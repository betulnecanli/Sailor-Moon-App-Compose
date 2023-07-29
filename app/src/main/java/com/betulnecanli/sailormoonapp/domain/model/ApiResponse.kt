package com.betulnecanli.sailormoonapp.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse(
    val success: Boolean?=true,
    val message: String? = "",
    val prevPage: Int?,
    val nextPage: Int?,
    val sailorMoon: List<SailorMoon> = emptyList(),
    val lastUpdated: Long? =0L
)
