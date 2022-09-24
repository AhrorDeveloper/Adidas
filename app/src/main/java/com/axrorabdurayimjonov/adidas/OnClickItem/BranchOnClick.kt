package com.axrorabdurayimjonov.adidas.OnClickItem

import com.axrorabdurayimjonov.adidas.models.BranchModel1

interface BranchOnClick {
    fun branchWorkerClick(model: BranchModel1)
    fun branchCall(call:BranchModel1)
    fun branchUpdate(update:BranchModel1)
}