package com.alifalpian.krakatauapp.presentation.technician.maintenance.form

import com.alifalpian.krakatauapp.domain.MaintenanceEquipment
import com.alifalpian.krakatauapp.domain.MaintenanceTools
import com.alifalpian.krakatauapp.domain.PreventiveCheckList
import com.alifalpian.krakatauapp.domain.SafetyMaintenance

data class MaintenanceFormTechnicianUiState(
    val equipment: MaintenanceEquipment? = null,
    val preventiveCheckList: List<PreventiveCheckList> = emptyList(),
    val toolsMaintenanceForm: List<MaintenanceTools> = emptyList(),
    val safetyMaintenanceForm: List<SafetyMaintenance> = emptyList()
)
