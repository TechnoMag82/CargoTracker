package api.intime

import com.squareup.moshi.Json

class InTime {

    @Json(name = "error")
    var error: Boolean? = null
    @Json(name = "error_message")
    var errorMessage: String? = null
    @Json(name = "data")
    var data: Data? = null

}
