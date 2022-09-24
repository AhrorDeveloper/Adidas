package com.axrorabdurayimjonov.adidas.OnClickItem

import com.axrorabdurayimjonov.adidas.models.ProviderDataModel


interface ProviderOnClick {
    fun providerMoney(pay:ProviderDataModel)
    fun providerUpdate(update:ProviderDataModel)
    fun providerCall(call:ProviderDataModel)



}
