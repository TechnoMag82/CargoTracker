package api.intime

import com.squareup.moshi.Json

class DateCreation {

    @Json(name = "date")
    var date: String? = null
    @Json(name = "timezone_type")
    var timezoneType: Int? = null
    @Json(name = "timezone")
    var timezone: String? = null

}
