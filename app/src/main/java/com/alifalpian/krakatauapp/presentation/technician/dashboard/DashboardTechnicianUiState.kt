package com.alifalpian.krakatauapp.presentation.technician.dashboard

import com.alifalpian.krakatauapp.domain.model.Resource
import com.alifalpian.krakatauapp.domain.model.TechnicianDashboardEquipment
import com.alifalpian.krakatauapp.domain.model.User

data class DashboardTechnicianUiState(
    val user: Resource<User> = Resource.Idling,
    val dashboardEquipments: Resource<List<TechnicianDashboardEquipment>> = Resource.Idling,
)
