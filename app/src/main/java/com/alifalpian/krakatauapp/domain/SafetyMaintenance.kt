package com.alifalpian.krakatauapp.domain

import com.alifalpian.krakatauapp.util.emptyString

data class SafetyMaintenance(
    val id: String = emptyString(),
    val description: String = emptyString(),
    val quantity: Int = 0,
    val unitOfMeasurement: Int = 0,
)
