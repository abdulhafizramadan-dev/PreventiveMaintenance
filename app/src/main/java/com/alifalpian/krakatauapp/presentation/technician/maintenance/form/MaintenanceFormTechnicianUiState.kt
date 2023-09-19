package com.alifalpian.krakatauapp.presentation.technician.maintenance.form

import com.alifalpian.krakatauapp.domain.model.MaintenanceEquipment
import com.alifalpian.krakatauapp.domain.model.MaintenanceTools
import com.alifalpian.krakatauapp.domain.model.PreventiveCheckList
import com.alifalpian.krakatauapp.domain.model.SafetyMaintenance

data class MaintenanceFormTechnicianUiState(
    val equipment: MaintenanceEquipment? = null,
    val preventiveCheckList: List<PreventiveCheckList> = emptyList(),
    val toolsMaintenanceForm: List<MaintenanceTools> = emptyList(),
    val safetyMaintenanceForm: List<SafetyMaintenance> = emptyList()
)
