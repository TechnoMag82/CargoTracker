package track_nova_posta_fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.cargo_status.*
import kotlinx.android.synthetic.main.fragment_track_nova_posta.*

import ukr.cargotracker.R
import android.view.inputmethod.InputMethodManager


/**
 * A simple [Fragment] subclass.
 */
class TrackNovaPostaFragment : Fragment(), ITrackView {

    private var trackPresenter: TrackPresenter? = null
    private var caption: String? = null

    private fun hideSoftKeyboard()
    {
        try {
            val inputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(activity.currentFocus!!.windowToken, 0)
        } catch (e: Exception) {
        }
    }

    override fun beginTrack() {
        llStatus.visibility = View.GONE
        progress1.visibility = View.VISIBLE
        btnTrack.isEnabled = false
    }

    override fun endTrack() {
        progress1.visibility = View.GONE
        btnTrack.isEnabled = true
    }

    override fun setStatus(ttN: String?, from: String?, to: String?, address: String?, status: String?, cost: String?) {
        llStatus.visibility = View.VISIBLE
        tvTTN.text = ttN
        tvFrom.text = from
        tvTo.text = to
        tvAddress.text = address
        tvStatus.text = status
        tvCost.text = cost
    }

    override fun setError(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    override fun getViewContext(): Context = context

    fun setAction(action: String?)
    {
        caption = action
    }

    override fun onDestroyView() {
        super.onDestroyView()
        trackPresenter?.destroyView()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        trackPresenter = TrackPresenter(this, caption)
        return inflater!!.inflate(R.layout.fragment_track_nova_posta, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        caption?.let {
            tvTrackCaption.text = caption
        }
        btnTrack.setOnClickListener {
            hideSoftKeyboard()
            trackPresenter?.trackTTN(editTTN.text.toString())
        }
    }

}
