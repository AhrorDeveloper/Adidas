package com.axrorabdurayimjonov.adidas.models

data class Warehouse_Products_Get_Model(
    val current_page: Int,
    val `data`: List<WarehouseProductData>,
    val limit: Int,
    val pages: Int
)