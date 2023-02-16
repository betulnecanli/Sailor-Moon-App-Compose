package com.betulnecanli.sailormoonapp.domain.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable
import com.betulnecanli.sailormoonapp.domain.model.SailorMoon

@Serializable
data class ApiResponse(
    @SerializedName("message")
    val message: String,
    @SerializedName("nextPage")
    val nextPage: Int,
    @SerializedName("prevPage")
    val prevPage: Int,
    @SerializedName("sailorMoon")
    val sailorMoon: List<SailorMoon>,
    @SerializedName("success")
    val success: Boolean
)
