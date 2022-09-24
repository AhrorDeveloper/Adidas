package com.axrorabdurayimjonov.adidas.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.axrorabdurayimjonov.adidas.OnClickItem.CategoryOnClick
import com.axrorabdurayimjonov.adidas.databinding.SettingCategoryItemBinding
import com.axrorabdurayimjonov.adidas.models.SettingGetModelData

class SettingGetCategoryAdapter(private val categoryList :List<SettingGetModelData>,private val uptade1:CategoryOnClick)
    :RecyclerView.Adapter<SettingGetCategoryAdapter.ViewHoldel>() {
        inner class ViewHoldel(val binding:SettingCategoryItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHoldel {
        return ViewHoldel(SettingCategoryItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHoldel, position: Int) {
        val categoryList = categoryList[position]
        holder.binding.categoryNameSettingItem.text = categoryList.name
        holder.binding.categoryPercentageSettingItem.text = categoryList.percent.toString()

        holder.binding.imageCategoryUpdate.setOnClickListener {
            uptade1.updateOnClick(categoryList)
        }
    }
    override fun getItemCount(): Int {
        return  categoryList.size
    }
}