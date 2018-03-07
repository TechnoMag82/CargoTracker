package database

import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.Database
import database.dao.TtnHistoryDao
import database.entity.TtnHistory


/**
 * Created by technomag on 03.03.18.
 */
@Database(entities = arrayOf(TtnHistory::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun historyTtnDao(): TtnHistoryDao
}