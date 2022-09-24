package com.axrorabdurayimjonov.adidas.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.axrorabdurayimjonov.adidas.OnClickItem.WarehousOnClick
import com.axrorabdurayimjonov.adidas.databinding.WarehouseItemBinding
import com.axrorabdurayimjonov.adidas.models.WarehouseModel

class WarehouseAdapter(private val warehouseList: List<WarehouseModel> , private val WarehouseOnClick:WarehousOnClick)
    :RecyclerView.Adapter<WarehouseAdapter.ViewHolder>() {

    inner class ViewHolder(val binding:WarehouseItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       return ViewHolder(WarehouseItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.textNameWarehouseItem.text=warehouseList[position].name
        holder.binding.textFromWarehouseItem.text = warehouseList[position].address
        holder.binding.imageCategoryWarehouseItem.setOnClickListener {
            WarehouseOnClick.wareOcClick(warehouseList[position])
        }
    }
    override fun getItemCount(): Int {
        return warehouseList.size
    }
}