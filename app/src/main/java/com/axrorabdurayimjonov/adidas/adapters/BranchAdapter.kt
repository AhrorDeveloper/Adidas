package com.axrorabdurayimjonov.adidas.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.axrorabdurayimjonov.adidas.OnClickItem.BranchOnClick
import com.axrorabdurayimjonov.adidas.databinding.BranchItemBinding
import com.axrorabdurayimjonov.adidas.models.BranchModel
import com.bumptech.glide.Glide

class BranchAdapter(
    private var branchList: BranchModel,
    private val branchOnClick: BranchOnClick
) : RecyclerView.Adapter<BranchAdapter.ViewHolder>() {


    inner class ViewHolder(val binding: BranchItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            BranchItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val branch = branchList.branch[position]

        if (branchList.logos.isNotEmpty()) {
            Glide.with(holder.itemView)
                .load("https://adidas.crud.uz/photos/${branchList.logos[0].logo}")
                .into(holder.binding.branchImageItem)
        }
        holder.binding.branchTextItem.text = branch.address
        holder.binding.branchPhoneNamberItem.text = branch.phone.toString()
        holder.binding.branchNameItem.text = branch.name
        holder.binding.branchPhoneItem.setOnClickListener {
            branchOnClick.branchCall(branch)
        }
        holder.binding.branchEditItem.setOnClickListener {
            branchOnClick.branchUpdate(branch)
        }

        holder.binding.branchWorkerItem.setOnClickListener {
            branchOnClick.branchWorkerClick(branch)
        }
        if (branch.balances != null) {
            val balance = branch.balances
            if (balance.isNotEmpty()) {
                holder.binding.branchMoneyTextItem.text =
                    "${balance[0].balance}  ${balance[0].currency?.currency}"
            }
        }

    }

    override fun getItemCount(): Int {
        return branchList.logos.size
    }
}