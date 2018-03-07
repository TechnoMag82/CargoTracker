package api.intime

import com.squareup.moshi.Json

class Data {

    @Json(name = "number")
    var number: String? = null
    @Json(name = "number2")
    var number2: String? = null
    @Json(name = "status_id")
    var statusId: String? = null
    @Json(name = "status")
    var status: String? = null
    @Json(name = "date_creation")
    var dateCreation: DateCreation? = null
    @Json(name = "date_delivery")
    var dateDelivery: DateDelivery? = null
    @Json(name = "date")
    var date: String? = null
    @Json(name = "type_id")
    var typeId: String? = null
    @Json(name = "type")
    var type: String? = null
    @Json(name = "from")
    var from: From? = null
    @Json(name = "to")
    var to: To? = null
    @Json(name = "cargo_type")
    var cargoType: String? = null
    @Json(name = "description")
    var description: String? = null
    @Json(name = "cost")
    var cost: String? = null
    @Json(name = "to_pay")
    var toPay: String? = null
    @Json(name = "cash_on_delivery")
    var cashOnDelivery: Int? = null
    @Json(name = "weight")
    var weight: String? = null
    @Json(name = "volume")
    var volume: String? = null
    @Json(name = "seats")
    var seats: String? = null
    @Json(name = "payer")
    var payer: String? = null

}
