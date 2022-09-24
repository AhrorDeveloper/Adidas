package com.axrorabdurayimjonov.adidas.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.axrorabdurayimjonov.adidas.OnClickItem.CurrentlyOnClick
import com.axrorabdurayimjonov.adidas.databinding.CurrentySettingItemBinding
import com.axrorabdurayimjonov.adidas.models.CurrentlyGetModelItem

class CurrentlyAdapter(private val currentlyList: ArrayList<CurrentlyGetModelItem>,private val updateCurr:CurrentlyOnClick) :
    RecyclerView.Adapter<CurrentlyAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: CurrentySettingItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            CurrentySettingItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.currentlyNameTextSettingItem.text = currentlyList[position].currency
        holder.binding.currentlyPercentageTextSettingItem.text = currentlyList[position].price.toString()
        holder.binding.updateCurrently.setOnClickListener {
            updateCurr.updateCurr(currentlyList[position])
        }
    }

    override fun getItemCount(): Int {
        return currentlyList.size
    }
}