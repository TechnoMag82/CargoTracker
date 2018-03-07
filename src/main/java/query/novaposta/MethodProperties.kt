package query.novaposta

import com.squareup.moshi.Json

class MethodProperties {

    @Json(name = "Documents")
    var documents: List<Document>? = null

}
