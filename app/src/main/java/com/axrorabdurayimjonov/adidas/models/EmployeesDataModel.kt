package com.axrorabdurayimjonov.adidas.models

import java.io.Serializable

data class EmployeesDataModel(
    val branch_id: Int,
    val id: Int,
    val name: String,
    val password_hash: String,
    val phone: Int,
    val profit_percentage: Int,
    val role: String,
    val status: Boolean,
    val token: String,
    val username: String
):Serializable