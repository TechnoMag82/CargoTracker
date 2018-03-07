package ukr.cargotracker

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import mainmenu.MainMenuFragment
import utils.MainFragmentManager

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MainFragmentManager.replaceFragment(this, MainMenuFragment(), true)
    }
}
