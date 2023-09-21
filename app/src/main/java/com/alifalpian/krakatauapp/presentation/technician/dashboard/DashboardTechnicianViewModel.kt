package com.alifalpian.krakatauapp.presentation.technician.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alifalpian.krakatauapp.domain.usecase.HomeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardTechnicianViewModel @Inject constructor(
    private val homeUseCase: HomeUseCase
) : ViewModel() {

    var dashboardTechnicianUiState = MutableStateFlow(DashboardTechnicianUiState())
        private set

    fun getUser(uid: String) {
        viewModelScope.launch {
            homeUseCase.getUser(uid).collect { resource ->
                dashboardTechnicianUiState.value = dashboardTechnicianUiState.value.copy(
                    user = resource
                )
            }
        }
    }

    fun getTechnicianDashboardEquipments(uid: String) {
        viewModelScope.launch {
            homeUseCase.getTechnicianDashboardEquipments(uid).collect { resource ->
                dashboardTechnicianUiState.value = dashboardTechnicianUiState.value.copy(
                    dashboardEquipments = resource
                )
            }
        }
    }
}