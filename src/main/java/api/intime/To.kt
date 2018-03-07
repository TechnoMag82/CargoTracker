package api.intime

import com.squareup.moshi.Json

class To {

    @Json(name = "city")
    var city: String? = null
    @Json(name = "store_code")
    var storeCode: String? = null
    @Json(name = "store")
    var store: Store? = null

}
