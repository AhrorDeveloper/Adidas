package com.axrorabdurayimjonov.adidas.models

data class CategoryGetSettingModel(
    val current_page: Int,
    val `data`: List<SettingGetModelData>,
    val limit: Int,
    val pages: Int
)