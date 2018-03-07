package query.novaposta

import com.squareup.moshi.Json

class Document {

    @Json(name = "DocumentNumber")
    var documentNumber: String? = null
    @Json(name = "Phone")
    var phone: String? = null

}
