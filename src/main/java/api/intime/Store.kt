package api.intime

import com.squareup.moshi.Json

class Store {

    @Json(name = "type")
    var type: String? = null
    @Json(name = "number")
    var number: Int? = null
    @Json(name = "address")
    var address: String? = null
    @Json(name = "url")
    var url: String? = null

}
