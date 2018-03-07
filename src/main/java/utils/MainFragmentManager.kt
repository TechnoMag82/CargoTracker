package utils

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import ukr.cargotracker.R

/**
 * Created by technomag on 08.06.17.
 */

object MainFragmentManager {

    fun pushFragment(activity: FragmentActivity, fragment: Fragment, tag: String) {
        val fTrans = activity.supportFragmentManager.beginTransaction()
        fTrans.replace(R.id.frActivities, fragment)
        fTrans.addToBackStack(tag)
        fTrans.commit()
    }

    fun replaceFragment(activity: FragmentActivity, frActivity: Fragment, addToBackstack: Boolean) {
        if (addToBackstack)
            activity.supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        val fTrans = activity.supportFragmentManager.beginTransaction()
        fTrans.replace(R.id.frActivities, frActivity)
        fTrans.commit()
    }

}
