package com.axrorabdurayimjonov.adidas.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.axrorabdurayimjonov.adidas.OnClickItem.ProviderOnClick
import com.axrorabdurayimjonov.adidas.databinding.ProviderItemBinding
import com.axrorabdurayimjonov.adidas.models.ProviderDataModel

class ProviderAdapter(private val providerModelList: List<ProviderDataModel>,private val providerOnClick:ProviderOnClick) :
    RecyclerView.Adapter<ProviderAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ProviderItemBinding) : RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ProviderItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val providerModel = providerModelList[position]
        holder.binding.providerTextNameItem.text = providerModel.name
        holder.binding.providerTextPhoneItem.text = providerModel.phone
        holder.binding.providerTextAddressItem.text = providerModel.address

        holder.binding.providerImageMoneyItem.setOnClickListener {
            providerOnClick.providerMoney(providerModelList[position])
        }
        holder.binding.providerImageEditItem.setOnClickListener {
            providerOnClick.providerUpdate(providerModelList[position])
        }
        holder.binding.providerImagePhoneItem.setOnClickListener {
            providerOnClick.providerCall(providerModelList[position])
        }

        if (providerModel.balances!=null){
            val balance = providerModel.balances
            if (balance.isNotEmpty()){
                holder.binding.providerTextMoneyItem.text = "${balance[0].balance}  ${balance[0].currency.currency}"
            }

        }

    }
    override fun getItemCount(): Int {
        return providerModelList.size
    }
}

