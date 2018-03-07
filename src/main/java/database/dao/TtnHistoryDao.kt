package database.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import database.entity.TtnHistory
import io.reactivex.Flowable


/**
 * Created by technomag on 03.03.18.
 */
@Dao
interface TtnHistoryDao {

    @Query("SELECT * FROM TtnHistory")
    fun getAll() : LiveData<List<TtnHistory>>

    @Insert
    fun insert(employee: TtnHistory)

    @Update
    fun update(employee: TtnHistory)

    @Delete
    fun delete(employee: TtnHistory)

    @Query("SELECT * FROM TtnHistory WHERE ttn = :number")
    fun isExist(number: String) : List<TtnHistory>

}