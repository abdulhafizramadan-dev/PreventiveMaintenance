package com.alifalpian.krakatauapp.domain.model

data class MaintenanceEquipment(
    val id: String,
    val order: String,
    val date: String,
    val interval: String,
    val execution: String,
    val location: String,
    val equipmentName: String,
    val technicianName: String,
)