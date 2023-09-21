package com.alifalpian.krakatauapp.domain.model

import com.alifalpian.krakatauapp.util.emptyString

data class Equipment(
    val documentId: String = emptyString(),
    val equipment: String = emptyString(),
    val date: String = emptyString(),
    val interval: String = emptyString(),
    val execution: String = emptyString(),
    val location: String = emptyString(),
    val description: String = emptyString(),
    val type: String = emptyString(),
    val uid: String = emptyString(),
)