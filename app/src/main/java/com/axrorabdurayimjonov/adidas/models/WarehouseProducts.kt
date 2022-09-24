package com.axrorabdurayimjonov.adidas.models

data class WarehouseProducts(
    val added_expense_price: Int,
    val articul: String,
    val category: Category,
    val category_id: Int,
    val currency: Currency,
    val currency_id: Int,
    val id: Int,
    val price: Int,
    val quantity: Int,
    val tan_narx: Int,
    val tan_narx_currency_id: Int,
    val warehouse_id: Int
)