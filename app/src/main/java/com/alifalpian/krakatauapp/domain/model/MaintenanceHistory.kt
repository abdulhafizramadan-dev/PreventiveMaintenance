package com.alifalpian.krakatauapp.domain.model

import com.alifalpian.krakatauapp.util.emptyString
import java.util.Date

data class MaintenanceHistory(
    val documentId: String = emptyString(),
    val technicianDocumentId: String = emptyString(),
    val employeeDocumentId: String = emptyString(),
    val maintenanceCheckPoint: String = emptyString(),
    val equipmentType: String = emptyString(),
    val equipmentDocumentId: String = emptyString(),
    val date: Date = Date(),
    val maintenanceCheckPointHistoryDocumentId: String = emptyString(),
    val status: String = emptyString(),
    val plantDuration: Date = Date(),
    val actualDuration: Date = Date()
)
