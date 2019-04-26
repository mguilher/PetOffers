package com.mdr.petoffers.models

data class AlertModel(
    val id: String? = null,
    val text: String,
    val startrange: Double,
    val endrange: Double
)