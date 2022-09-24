package com.axrorabdurayimjonov.adidas.classes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.axrorabdurayimjonov.adidas.R
import com.axrorabdurayimjonov.adidas.api.ApiClient
import com.axrorabdurayimjonov.adidas.databinding.EmployeeDialogAddBinding
import com.axrorabdurayimjonov.adidas.models.EmployeePostModel
import kotlinx.android.synthetic.main.employee_dialog_add.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DialogEmployeeAdd(val branchId : Int) : DialogFragment() {
    private lateinit var binding: EmployeeDialogAddBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = EmployeeDialogAddBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val role = resources.getStringArray(R.array.role)
        val arrayAdapter = ArrayAdapter(requireContext(),R.layout.dropdown_item,role)
        binding.autoCompleteTextView.setAdapter(arrayAdapter)

        binding.exitEmployeeDialog.setOnClickListener {
            dismiss()
        }

        binding.okEmployeeDialog.setOnClickListener {

            when {

                binding.nameEmployeeDialog.text.isEmpty() -> binding.nameEmployeeDialog.error =
                    "Ismni kiriting"
                binding.phoneEmployeeDialog.text.isEmpty() -> binding.phoneEmployeeDialog.error =
                    "Telefon nomerni kiriting"
                binding.foizEmployeeDialog.text.isEmpty() -> binding.foizEmployeeDialog.error =
                    "Foiz maoshini kiriting"
                binding.loginEmployeeDialog.text.isEmpty() -> binding.loginEmployeeDialog.error =
                    "Loginni kiriting"
                binding.passwordEmployeeDialog.text.isEmpty() -> binding.passwordEmployeeDialog.error =
                    "Parolni kiriting"
                else -> {
                    val username = binding.nameEmployeeDialog.text.toString().trim()
                    val phone = binding.phoneEmployeeDialog.text.toString().toInt()
                    val foiz = binding.foizEmployeeDialog.text.toString().toInt()
                    val login = binding.loginEmployeeDialog.text.toString().trim()
                    val parol = binding.passwordEmployeeDialog.text.toString().trim()
                    val selectdItem = binding.autoCompleteTextView.text.toString()
                    if (selectdItem=="Sotuvchi"){
                        val postEmployeePostModel=EmployeePostModel(branchId,0,username,parol,phone,foiz,"seller",true,login)
                        postEmployee(postEmployeePostModel)
                    }else{
                        val postEmployeePostModel=EmployeePostModel(branchId,0,username,parol,phone,foiz,"branch_id",true,login)
                        postEmployee(postEmployeePostModel)
                    }

                }
            }
        }
    }

    private fun postEmployee(postEmployeePostModel: EmployeePostModel) {
       ApiClient.getRetrofit().postEmployee(postEmployeePostModel).enqueue(object :Callback<EmployeePostModel>{
           override fun onResponse(
               call: Call<EmployeePostModel>,
               response: Response<EmployeePostModel>
           ) {
             if (response.code()==200){
                 dismiss()
                 Toast.makeText(
                     binding.root.context,
                     "Hodim qoshildi!",
                     Toast.LENGTH_SHORT
                 ).show()
             }
           }
           override fun onFailure(call: Call<EmployeePostModel>, t: Throwable) {
               dismiss()
               Toast.makeText(
                   binding.root.context,
                   "Xato",
                   Toast.LENGTH_SHORT
               ).show()
           }
       })
    }
}


