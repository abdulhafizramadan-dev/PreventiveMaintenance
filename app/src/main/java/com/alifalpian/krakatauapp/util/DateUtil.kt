package com.alifalpian.krakatauapp.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Date.toMaintenanceDateFormat(pattern: String = "MM/dd/yyyy"): String {
    val formatter = SimpleDateFormat(pattern, Locale.getDefault())
    return formatter.format(this)
}