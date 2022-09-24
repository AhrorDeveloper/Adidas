package com.axrorabdurayimjonov.adidas.models

data class ProviderBalanceModel(
    val balance: Int,
    val currency: ProviderCurrencyModel,
    val currency_id: Int,
    val id: Int,
    val market_id: Int
)