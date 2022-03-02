package com.inc.vr.corp.app.quranwidget.model.main

import com.google.gson.annotations.SerializedName


data class ModelWidget(

    // Use @SerializedName(" ") for the Gson converter
    // @field:Json(name = " ") for the Moshi converter
    // @SerialName(" ") for the Kotlinx Serialization converter
    // @JsonProperty(" ") for the Jackson converter

    @SerializedName("ar")
    var arab: String?,

    @SerializedName("id")
    var indo: String?,

    @SerializedName("nomor")
    var nomor: String?,

    @SerializedName("tr")
    var latin: String?

) {
    fun count() {

    }
}