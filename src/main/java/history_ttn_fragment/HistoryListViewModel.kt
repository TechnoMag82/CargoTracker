package history_ttn_fragment

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import database.entity.TtnHistory
import ukr.cargotracker.CargoTrackerApp

/**
 * Created by technomag on 04.03.18.
 */
class HistoryListViewModel : ViewModel() {
    private var mNoteLiveData: LiveData<List<TtnHistory>>? = null
    fun getAllNotes(): LiveData<List<TtnHistory>> {
        if (mNoteLiveData == null) {
            mNoteLiveData = CargoTrackerApp.appDatabase.historyTtnDao().getAll()
        }
        return mNoteLiveData!!
    }
}