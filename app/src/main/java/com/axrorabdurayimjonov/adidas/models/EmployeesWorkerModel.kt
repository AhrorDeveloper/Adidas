package com.axrorabdurayimjonov.adidas.models

data class EmployeesWorkerModel(
    val current_page: Int,
    val `data`: List<EmployeesDataModel>,
    val limit: Int,
    val pages: Int
)