package com.alifalpian.krakatauapp.domain.model

import com.alifalpian.krakatauapp.util.emptyString

data class MaintenanceHistory(
    val documentId: String = emptyString(),
    val technicianDocumentId: String = emptyString(),
    val maintenanceCheckPoint: String = emptyString(),
    val type: String = emptyString(),
    val equipmentDocumentId: String = emptyString(),
    val date: String = emptyString(),
    val maintenanceCheckPointHistoryDocumentId: String = emptyString(),
)
