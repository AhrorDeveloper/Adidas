package com.axrorabdurayimjonov.adidas.models

import java.io.Serializable

data class BranchModel1(
    val address: String,
    val balances: List<BranchBalance>,
    val colour: String,
    val colour2: String,
    val font: String,
    val id: Int,
    val map_lat: String,
    val map_long: String,
    val name: String,
    val phone: Int,
    val trade_currency: Int,
    val user_id: Int
):Serializable