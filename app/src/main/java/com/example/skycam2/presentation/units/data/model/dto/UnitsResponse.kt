package com.example.skycam2.presentation.units.data.model.dto

import com.google.gson.annotations.SerializedName

data class UnitsResponse(
    @SerializedName("Data") val Data: List<DataCamera>,
    @SerializedName("Status") val Status: String
)

data class DataCamera(
@SerializedName("company") val company: String,
@SerializedName("idCamera") val idCamera: String,
@SerializedName("nameCamera") val nameCamera: String,
@SerializedName("online") val online: String,
@SerializedName("plate") val plate: String,
@SerializedName("subCompany") val subCompany: String,
@SerializedName("totalCamaras") val totalCamaras: String,
@SerializedName("channels") val channels: List<Channel>,
var isVisible: Boolean = false
)

data class Channel(
 @SerializedName("idChannel") val idChannel: String,
 @SerializedName("linkChannel") val linkChannel: String,
 @SerializedName("nameChannel") val nameChannel: String
)
