package com.alifalpian.krakatauapp.domain

data class PreventiveCheckList(
    val id: String,
    val isChecked: Boolean = false,
    val text: String
)
