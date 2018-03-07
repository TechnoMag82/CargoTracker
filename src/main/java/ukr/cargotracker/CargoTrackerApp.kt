package ukr.cargotracker

import android.app.Application
import database.AppDatabase
import android.arch.persistence.room.Room

/**
 * Created by technomag on 03.03.18.
 */
class CargoTrackerApp : Application() {

    companion object {
        lateinit var appInstance: CargoTrackerApp
        lateinit var appDatabase: AppDatabase
    }

    override fun onCreate() {
        super.onCreate()
        appInstance = this
        appDatabase = Room.databaseBuilder(this, AppDatabase::class.java, "CargoTracker.db")
                .build()
    }
}