package database.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by technomag on 03.03.18.
 */
@Entity
class TtnHistory {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    var ttn: String = ""
    var fromCity: String = ""
    var toCity: String = ""
    var status: String = ""
    var cost: String = ""
    var address: String = ""
    var carrier: Int = 0 // 0 - InTime, 1 - NovaPosta

}