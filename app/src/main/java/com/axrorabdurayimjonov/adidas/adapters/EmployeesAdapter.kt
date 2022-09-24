package com.axrorabdurayimjonov.adidas.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.axrorabdurayimjonov.adidas.OnClickItem.EmployeOnClick
import com.axrorabdurayimjonov.adidas.databinding.EmployeeItemBinding
import com.axrorabdurayimjonov.adidas.models.EmployeesDataModel

class EmployeesAdapter(private val employeesList: List<EmployeesDataModel>,private val EmployeeOnClick:EmployeOnClick):RecyclerView.Adapter<EmployeesAdapter.ViewHolder>() {

    inner class ViewHolder (val binding:EmployeeItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(EmployeeItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (employeesList[position]!=null){
        holder.binding.employeeTextItem.text = employeesList[position].name
        holder.binding.employeeTextPhoneItem.text = employeesList[position].phone.toString()
        holder.binding.employeeTextWorkItem.text = employeesList[position].role
        holder.binding.employeeTextPercentItem.text = employeesList[position].profit_percentage.toString()}
        holder.binding.employeePhoneItem.setOnClickListener {
            EmployeeOnClick.EmployeeCall(employeesList[position])
        }
    }
    override fun getItemCount(): Int {
        return employeesList.size
    }

}