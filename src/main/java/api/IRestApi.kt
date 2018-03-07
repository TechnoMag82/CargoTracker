package api

import api.intime.InTime
import api.novaposta.NovaPosta
import query.novaposta.QueryNovaPosta
import retrofit2.Call
import retrofit2.http.*
import rx.Observable


/**
 * Created by technomag on 21.02.18.
 */
interface IRestApi {

    @POST("track")
    @FormUrlEncoded
    fun trackTtn(@Field("ttn") ttn: String, @Field("lang") lang: String): Observable<InTime>

    // APIkey Nova Posta - 009ec491144ad53951c2a7cf8d888d03
    @POST("json/")
    @Headers("Accept: application/json", "Content-Type: application/json")
    fun calcBilling(@Header("Content-Type") df: String, @Body declaration: QueryNovaPosta): Observable<NovaPosta>

}