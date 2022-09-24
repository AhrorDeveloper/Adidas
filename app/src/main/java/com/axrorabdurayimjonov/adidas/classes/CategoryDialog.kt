package com.axrorabdurayimjonov.adidas.classes

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.axrorabdurayimjonov.adidas.api.ApiClient
import com.axrorabdurayimjonov.adidas.databinding.DialogCategorySettingBinding
import com.axrorabdurayimjonov.adidas.models.CategoryPostModel
import com.axrorabdurayimjonov.adidas.models.SettingGetModelData
import com.axrorabdurayimjonov.adidas.models.UpdateCategoryModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryDialog(private var marketCar:SettingGetModelData?) : DialogFragment() {
    private lateinit var binding: DialogCategorySettingBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(
            DialogFragment.STYLE_NO_TITLE,
            android.R.style.Theme_Holo_Light_Dialog_NoActionBar_MinWidth
        )
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogCategorySettingBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (marketCar !=null){
            updateUi()
        }

        binding.exitCategoryDialog.setOnClickListener {
            dismiss()
        }
        binding.okCategoryDialog.setOnClickListener {
            when {
                binding.categoryNameTextSettingDialog.text.isEmpty() -> binding.categoryNameTextSettingDialog.error =
                    "Categoryyani kiriting"
                binding.categoryPercentageTextSettingDialog.text.isEmpty() -> binding.categoryPercentageTextSettingDialog.error =
                    "Foizni kiriting"
                else -> {
                    val categoryName = binding.categoryNameTextSettingDialog.text.toString().trim()
                    val categoryPercent = binding.categoryPercentageTextSettingDialog.text.toString().toInt()
                    if (marketCar==null){
                        val postCategory = CategoryPostModel(categoryName, categoryPercent)
                        postCategory(postCategory)
                    }else{
                        val updateCategory = UpdateCategoryModel(categoryName, categoryPercent)
                        updateCategory(marketCar!!.id,updateCategory)
                    }
                }

            }
        }
    }

    private fun updateCategory(marketId: Int, updateCategory: UpdateCategoryModel) {
        ApiClient.getRetrofit().updateCategory(marketId,updateCategory).enqueue(object :Callback<UpdateCategoryModel>{

            override fun onResponse(
                call: Call<UpdateCategoryModel>,
                response: Response<UpdateCategoryModel>
            ) {
                if (response.code()==200){
                    dismiss()
                    Toast.makeText(
                        binding.root.context,
                        "Category yangilandi",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            override fun onFailure(call: Call<UpdateCategoryModel>, t: Throwable) {
            }
        })
    }
    private fun postCategory(postCategory: CategoryPostModel) {
        ApiClient.getRetrofit().categoryPost(postCategory)
            .enqueue(object : Callback<CategoryPostModel> {

                override fun onResponse(
                    call: Call<CategoryPostModel>,
                    response: Response<CategoryPostModel>
                ) {
                    if (response.code() == 200) {
                        dismiss()
                        Log.e(ContentValues.TAG, "onResponse: start recycler123 ${response.body()}")
                    }
                }

                override fun onFailure(call: Call<CategoryPostModel>, t: Throwable) {
                    Toast.makeText(
                        binding.root.context,
                        "Category Qoshilmadi",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })

    }
    private fun updateUi() {
        binding.categoryNameTextSettingDialog.setText(marketCar!!.name)
        binding.categoryPercentageTextSettingDialog.setText(marketCar!!.percent.toString())
    }


}