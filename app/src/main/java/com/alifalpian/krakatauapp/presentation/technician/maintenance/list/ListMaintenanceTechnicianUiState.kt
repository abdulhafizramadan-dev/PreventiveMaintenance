package com.alifalpian.krakatauapp.presentation.technician.maintenance.list

import com.alifalpian.krakatauapp.domain.model.Equipment
import com.alifalpian.krakatauapp.domain.model.Resource

data class ListMaintenanceTechnicianUiState(
    val equipmentsWillMaintenance: Resource<List<Equipment>> = Resource.Loading,
    val equipmentsHasBeenMaintenance: Resource<List<Equipment>> = Resource.Loading,
)
