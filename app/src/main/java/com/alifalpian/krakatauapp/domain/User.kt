package com.alifalpian.krakatauapp.domain

data class User(
    val id: String,
    val type: String,
    val photo: String,
    val name: String,
    val division: String,
    val nik: String
)
