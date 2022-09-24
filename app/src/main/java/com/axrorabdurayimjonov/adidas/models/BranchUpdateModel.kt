package com.axrorabdurayimjonov.adidas.models

data class BranchUpdateModel(
    val address: String,
    val colour: String,
    val font: String,
    val map_lat: String,
    val map_long: String,
    val name: String,
    val phone: Int,
    val trade_currency: Int
)