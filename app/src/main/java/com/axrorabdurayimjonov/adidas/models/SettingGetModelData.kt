package com.axrorabdurayimjonov.adidas.models

import java.io.Serializable

data class SettingGetModelData(
    val id: Int,
    val name: String,
    val percent: Int,
    val user_id: Int
):Serializable