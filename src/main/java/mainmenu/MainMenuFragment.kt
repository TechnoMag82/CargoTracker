package mainmenu


import android.app.Activity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import history_ttn_fragment.HistoryTtnFragment
import kotlinx.android.synthetic.main.fragment_main_menu.*
import track_nova_posta_fragment.TrackNovaPostaFragment
import ukr.cargotracker.AboutFragment
import ukr.cargotracker.MainActivity

import ukr.cargotracker.R
import utils.MainFragmentManager


class MainMenuFragment : Fragment() {

    private var currentAction : String = ""
    private lateinit var activity : MainActivity

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        savedInstanceState?.let {
            currentAction = savedInstanceState?.getString("Action", "")!!
            setAction(currentAction)
        }
    }

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        this.activity = activity as MainActivity
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_main_menu, container, false)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.putString("Action", currentAction)
        super.onSaveInstanceState(outState)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvInTime.setOnClickListener{
            setAction("InTime")
        }

        tvNovaPosta.setOnClickListener{
            setAction("TrackNovaPosta")
        }

        imgAbout.setOnClickListener{
            setAction("About")
        }

        tvTtnHistory.setOnClickListener {
            setAction("TtnHistory")
        }
    }

    private fun setAction(action: String)
    {
        currentAction = action
        when (action)
        {
            "InTime" -> {
                var frInTime = TrackNovaPostaFragment()
                frInTime.setAction(tvInTime.text.toString())
                MainFragmentManager.pushFragment(activity, frInTime, "InTime")
            }
            "TrackNovaPosta" -> {
                var frNovaPosta = TrackNovaPostaFragment()
                frNovaPosta.setAction(tvNovaPosta.text.toString())
                MainFragmentManager.pushFragment(activity, frNovaPosta, "TrackNovaPosta")
            }
            "About" -> MainFragmentManager.pushFragment(activity, AboutFragment(), "About")
            "TtnHistory" -> MainFragmentManager.pushFragment(activity, HistoryTtnFragment(), "TtnHistory")
        }
    }
}
