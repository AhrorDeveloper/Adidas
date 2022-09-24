package com.axrorabdurayimjonov.adidas.models

import java.io.Serializable

data class ProviderModel(
    val current_page: Int,
    val data: List<ProviderDataModel>,
    val limit: Int,
    val pages: Int
): Serializable