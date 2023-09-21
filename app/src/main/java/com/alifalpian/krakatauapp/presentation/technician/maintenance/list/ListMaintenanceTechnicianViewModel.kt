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

    fun getEquipmentsWillBeMaintenance(uid: String) {
        viewModelScope.launch {
            homeUseCase.getEquipmentsWillBeMaintenance(uid).collect { resource ->
                listMaintenanceTechnicianUiState.value = listMaintenanceTechnicianUiState.value.copy(
                    equipmentsWillMaintenance = resource
                )
            }
        }
    }

}