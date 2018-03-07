package api.novaposta

import com.squareup.moshi.Json

class NovaPosta {

    @Json(name = "success")
    var success: Boolean? = null
    @Json(name = "data")
    var data: List<Datum>? = null

}
