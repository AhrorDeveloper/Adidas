package com.axrorabdurayimjonov.adidas.models

data class BranchBalance(
    val balance: Int,
    val branch_id: Int,
    val currency: CurrencyX,
    val currency_id: Int,
    val id: Int
)