package com.alifalpian.krakatauapp.domain.model

data class PreventiveCheckList(
    val id: String,
    val isChecked: Boolean = false,
    val text: String
)
