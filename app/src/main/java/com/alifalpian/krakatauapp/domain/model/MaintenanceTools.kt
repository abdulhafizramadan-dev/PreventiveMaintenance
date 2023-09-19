package com.alifalpian.krakatauapp.domain.model

import com.alifalpian.krakatauapp.util.emptyString

data class MaintenanceTools(
    val id: String = emptyString(),
    val description: String = emptyString(),
    val quantity: Int = 0,
    val unitOfMeasurement: Int = 0,
)
