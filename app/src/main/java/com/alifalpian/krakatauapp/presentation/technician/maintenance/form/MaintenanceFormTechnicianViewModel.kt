package com.alifalpian.krakatauapp.presentation.technician.maintenance.form

import androidx.lifecycle.ViewModel
import com.alifalpian.krakatauapp.domain.MaintenanceEquipment
import com.alifalpian.krakatauapp.domain.MaintenanceTools
import com.alifalpian.krakatauapp.domain.SafetyMaintenance
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class MaintenanceFormTechnicianViewModel @Inject constructor() : ViewModel() {

    var maintenanceFormTechnicianUiState = MutableStateFlow(MaintenanceFormTechnicianUiState())
        private set

    fun updateMaintenanceFormTechnicianUiState(equipment: MaintenanceEquipment) {
        maintenanceFormTechnicianUiState.value = maintenanceFormTechnicianUiState.value.copy(
            equipment = equipment
        )
    }

    fun addToolsMaintenanceForm() {
        val toolsMaintenance = maintenanceFormTechnicianUiState.value.toolsMaintenanceForm.toMutableList()
        toolsMaintenance.add(MaintenanceTools())
        maintenanceFormTechnicianUiState.value = maintenanceFormTechnicianUiState.value.copy(
            toolsMaintenanceForm = toolsMaintenance
        )
    }

    fun addSafetyMaintenanceTools() {
        val safetyMaintenanceTools = maintenanceFormTechnicianUiState.value.safetyMaintenanceForm.toMutableList()
        safetyMaintenanceTools.add(SafetyMaintenance())
        maintenanceFormTechnicianUiState.value = maintenanceFormTechnicianUiState.value.copy(
            safetyMaintenanceForm = safetyMaintenanceTools
        )
    }

    fun removeLastToolsMaintenanceForm() {
        val toolsMaintenance = maintenanceFormTechnicianUiState.value.toolsMaintenanceForm.toMutableList()
        toolsMaintenance.removeLast()
        maintenanceFormTechnicianUiState.value = maintenanceFormTechnicianUiState.value.copy(
            toolsMaintenanceForm = toolsMaintenance
        )
    }

    fun removeLastSafetyMaintenanceTools() {
        val safetyMaintenanceTools = maintenanceFormTechnicianUiState.value.safetyMaintenanceForm.toMutableList()
        safetyMaintenanceTools.removeLast()
        maintenanceFormTechnicianUiState.value = maintenanceFormTechnicianUiState.value.copy(
            safetyMaintenanceForm = safetyMaintenanceTools
        )
    }

    fun updateToolsMaintenanceForm(index: Int, maintenanceTools: MaintenanceTools) {
        val toolsMaintenance = maintenanceFormTechnicianUiState.value.toolsMaintenanceForm.toMutableList()
        toolsMaintenance[index] = maintenanceTools
        maintenanceFormTechnicianUiState.value = maintenanceFormTechnicianUiState.value.copy(
            toolsMaintenanceForm = toolsMaintenance
        )
    }

    fun updateSafetyMaintenanceForm(index: Int, safetyTools: SafetyMaintenance) {
        val toolsMaintenance = maintenanceFormTechnicianUiState.value.safetyMaintenanceForm.toMutableList()
        toolsMaintenance[index] = safetyTools
        maintenanceFormTechnicianUiState.value = maintenanceFormTechnicianUiState.value.copy(
            safetyMaintenanceForm = toolsMaintenance
        )
    }

}