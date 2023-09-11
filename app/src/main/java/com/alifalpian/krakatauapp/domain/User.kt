package com.alifalpian.krakatauapp.domain

enum class UserType {
    Technician, Employee
}

data class User(
    val id: String,
    val type: String,
    val photo: String,
    val name: String,
    val division: String,
    val nik: String,
)
