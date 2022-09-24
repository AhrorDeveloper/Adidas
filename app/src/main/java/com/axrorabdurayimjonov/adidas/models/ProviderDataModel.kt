package com.axrorabdurayimjonov.adidas.models

import java.io.Serializable

data class ProviderDataModel(
    val address: String,
    val balances: List<ProviderBalanceModel>,
    val id: Int,
    val name: String,
    val phone: String,
    val user_id: Int
): Serializable