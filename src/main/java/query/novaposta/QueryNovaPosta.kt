package query.novaposta

import com.squareup.moshi.Json

class QueryNovaPosta {

    @Json(name = "apiKey")
    var apiKey: String? = null
    @Json(name = "modelName")
    var modelName: String? = null
    @Json(name = "calledMethod")
    var calledMethod: String? = null
    @Json(name = "methodProperties")
    var methodProperties: MethodProperties? = null

}
