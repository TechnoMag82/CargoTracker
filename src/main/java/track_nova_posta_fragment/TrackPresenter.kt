package track_nova_posta_fragment

import api.IRestApi
import api.intime.InTime
import api.novaposta.NovaPosta
import database.entity.TtnHistory
import okhttp3.OkHttpClient
import query.novaposta.QueryNovaPosta
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import rx.Observable
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import rx.subscriptions.CompositeSubscription
import query.novaposta.MethodProperties
import query.novaposta.Document
import rx.Completable
import ukr.cargotracker.CargoTrackerApp
import java.util.ArrayList
import rx.functions.Action0
import ukr.cargotracker.R


/**
 * Created by technomag on 21.02.18.
 */

class TrackPresenter(iTrackView: ITrackView, action: String?)
{

    private val mAction : String? = action
    private var trackView : ITrackView? = iTrackView

    private var compositeSubscription : CompositeSubscription = CompositeSubscription()

    fun trackTTN(ttn: String)
    {
        ttn?.let {
            if (valid(ttn)) {
                trackView?.beginTrack()
                when (mAction) {
                    "Ин Тайм" -> doRequestInTime(ttn)
                    "Новая Почта" -> doRequestNovaPosta(ttn)
                    else -> trackView?.setError(trackView?.getViewContext()?.resources?.getString(R.string.carrier_not_support)!!)
                }
            }
        }
    }

    private fun valid(ttn: String) : Boolean
    {
        if (ttn?.isEmpty()) {
            trackView?.setError(trackView?.getViewContext()?.resources?.getString(R.string.number_ttn)!!)
            return false
        }
        else
            return true
    }

    private fun doRequestNovaPosta(ttn: String) {

        var query = QueryNovaPosta()
        query.apiKey = "" // you must create API key yourself in Nova Posta portal
        query.modelName = "TrackingDocument"
        query.calledMethod = "getStatusDocuments"
        query.methodProperties = MethodProperties()
        var document = Document()
        document.documentNumber = ttn
        document.phone = ""
        query.methodProperties?.documents = ArrayList<Document>()
        (query.methodProperties?.documents as ArrayList<Document>)?.add(document)

        var client: OkHttpClient = OkHttpClient.Builder()
                .build()

        val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl("https://api.novaposhta.ua/v2.0/")
                .client(client)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()

        val service: IRestApi = retrofit.create(IRestApi::class.java!!)

        val call: Observable<NovaPosta> = service.calcBilling("application/json", query)

        val subscription = call.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe ( object : Subscriber<NovaPosta>(){
                    override fun onCompleted() {
                        trackView?.endTrack()
                    }

                    override fun onNext(t: NovaPosta?) {

                        t?.let {

                            var ttnItem = TtnHistory()
                            ttnItem.cost = t?.data?.get(0)?.documentCost.toString()
                            ttnItem.fromCity = t?.data?.get(0)?.citySender!!
                            ttnItem.toCity = t?.data?.get(0)?.cityRecipient!!
                            ttnItem.status = t?.data?.get(0)?.status!!
                            ttnItem.address = t?.data?.get(0)?.warehouseRecipient!!
                            ttnItem.ttn = t.data?.get(0)?.number!!
                            ttnItem.carrier = 1

                            insertTtn(ttnItem)

                            trackView?.setStatus(t.data?.get(0)?.number,
                                    t?.data?.get(0)?.citySender,
                                    t?.data?.get(0)?.cityRecipient,
                                    t?.data?.get(0)?.warehouseRecipient,
                                    t?.data?.get(0)?.status,
                                    t?.data?.get(0)?.documentCost.toString())
                        }
                    }

                    override fun onError(e: Throwable?) {
                        trackView?.endTrack()
                        trackView?.setError(trackView?.getViewContext()?.resources?.getString(R.string.error_request)!!)
                    }
                })
        compositeSubscription.add(subscription)
    }

    fun destroyView()
    {
        compositeSubscription.unsubscribe()
        compositeSubscription.clear()
    }

    private fun doRequestInTime(ttn: String)
    {
        var client: OkHttpClient = OkHttpClient.Builder()
                .build()

        val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl("") // sorry, base address is secret by NDA
                .client(client)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()

        val service: IRestApi = retrofit.create(IRestApi::class.java!!)

        val call: Observable<InTime> = service.trackTtn(ttn, "ru")

        val subscription = call.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe ( object : Subscriber<InTime>(){
                    override fun onCompleted() {
                        trackView?.endTrack()
                    }

                    override fun onNext(t: InTime?) {
                        t?.let {

                            var ttnItem = TtnHistory()
                            ttnItem.cost = t!!.data!!.cost!!
                            ttnItem.fromCity = t!!.data!!.from!!.city!!
                            ttnItem.toCity = t!!.data!!.to!!.city!!
                            ttnItem.status = t!!.data!!.status!!
                            ttnItem.address = t!!.data!!.to!!.store!!.address!!
                            ttnItem.ttn = t!!.data!!.number!!
                            ttnItem.carrier = 0

                            insertTtn(ttnItem)

                            trackView?.setStatus(t!!.data!!.number,
                                    t!!.data!!.from!!.city,
                                    t!!.data!!.to!!.city,
                                    t!!.data!!.to!!.store!!.address,
                                    t!!.data!!.status,
                                    t!!.data!!.cost)
                        }
                    }

                    override fun onError(e: Throwable?) {
                        //Log.d("Track", e?.message!!)
                        trackView?.endTrack()
                        trackView?.setError(trackView?.getViewContext()?.resources?.getString(R.string.error_request)!!)
                    }
                })
        compositeSubscription.add(subscription)
    }

    private fun insertTtn(ttnItem : TtnHistory)
    {
        Completable.fromAction(Action0 {
            var ttnH1 = CargoTrackerApp.appDatabase.historyTtnDao().isExist(ttnItem.ttn)
            if (ttnH1.isEmpty())
                CargoTrackerApp.appDatabase.historyTtnDao().insert(ttnItem)
            else {
                ttnItem.id = ttnH1[0].id
                CargoTrackerApp.appDatabase.historyTtnDao().update(ttnItem)
            }
        })
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe({ })
    }

}
