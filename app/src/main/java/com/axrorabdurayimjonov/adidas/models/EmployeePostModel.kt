package com.axrorabdurayimjonov.adidas.models

data class EmployeePostModel(
    val branch_id: Int,
    val id: Int,
    val name: String,
    val password: String,
    val phone: Int,
    val profit_percentage: Int,
    val role: String,
    val status: Boolean,
    val username: String
)