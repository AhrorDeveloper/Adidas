package com.axrorabdurayimjonov.adidas.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.axrorabdurayimjonov.adidas.databinding.ProviderMoneyItemBinding
import com.axrorabdurayimjonov.adidas.models.ProviderBalanceModel

class ProviderPayMoneyAdapter(private val payMoneyList:List<ProviderBalanceModel>):RecyclerView.Adapter<ProviderPayMoneyAdapter.ViewHolder>() {
    inner class ViewHolder(val binding:ProviderMoneyItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ProviderMoneyItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pay = payMoneyList[position]

        if (pay!=null){
                holder.binding.providerTextPayMoneyItem.text = "${pay.balance} ${pay.currency.currency}"
        }
    }

    override fun getItemCount(): Int {
       return payMoneyList.size
    }
}