package com.alifalpian.krakatauapp.presentation.technician.maintenance.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alifalpian.krakatauapp.domain.usecase.HomeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListMaintenanceTechnicianViewModel @Inject constructor(
    private val homeUseCase: HomeUseCase
) : ViewModel() {

    var listMaintenanceTechnicianUiState = MutableStateFlow(ListMaintenanceTechnicianUiState())
        private set

    fun getEquipmentsWillBeMaintenance(technicianDocumentId: String) {
        viewModelScope.launch {
            homeUseCase.getEquipmentsWillBeMaintenance(technicianDocumentId).collect { resource ->
                listMaintenanceTechnicianUiState.value = listMaintenanceTechnicianUiState.value.copy(
                    equipmentsWillMaintenance = resource
                )
            }
        }
    }

    fun getEquipmentsHasBeenMaintenance(technicianDocumentId: String) {
        viewModelScope.launch {
            homeUseCase.getEquipmentsHasBeenMaintenance(technicianDocumentId).collect { resource ->
                listMaintenanceTechnicianUiState.value = listMaintenanceTechnicianUiState.value.copy(
                    equipmentsHasBeenMaintenance = resource
                )
            }
        }
    }

}