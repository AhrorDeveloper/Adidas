package com.axrorabdurayimjonov.adidas.models

data class LoginModel(
    val access_token: String,
    val branch_id: Int,
    val id: Int,
    val role: String,
    val token_type: String
)