package com.axrorabdurayimjonov.adidas.classes

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.axrorabdurayimjonov.adidas.api.ApiClient
import com.axrorabdurayimjonov.adidas.databinding.DialogCurrenteSettingBinding
import com.axrorabdurayimjonov.adidas.models.CurrentePostModel
import com.axrorabdurayimjonov.adidas.models.CurrentlyGetModelItem
import com.axrorabdurayimjonov.adidas.models.CurrentlyUpdateModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CurrenceDialog(private val marketCurr: CurrentlyGetModelItem?) : DialogFragment() {
    private lateinit var binding: DialogCurrenteSettingBinding

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
        binding = DialogCurrenteSettingBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (marketCurr != null){
            updateUi()
        }

        binding.exitCurrentlyDialog.setOnClickListener {
            dismiss()
        }
        binding.okCurrentlyDialog.setOnClickListener {
            when {
                binding.currentlyNameTextSettingDialog.text.isEmpty() -> binding.currentlyNameTextSettingDialog.error =
                    "Categoryyani kiriting"
                binding.currentlyPercentageTextSettingDialog.text.isEmpty() -> binding.currentlyPercentageTextSettingDialog.error =
                    "Foizni kiriting"
                else -> {
                    val categoryName = binding.currentlyNameTextSettingDialog.text.toString().trim()
                    val categoryPercent = binding.currentlyPercentageTextSettingDialog.text.toString().toInt()


                    if (marketCurr == null){
                        val postCategory = CurrentePostModel(categoryName, categoryPercent)
                        postCategory(postCategory)
                    }else{
                        val putCurrently=CurrentlyUpdateModel(categoryName,categoryPercent)
                        updateCurrently(marketCurr.id,putCurrently)
                    }
                }

            }
        }
    }

    private fun updateCurrently(id: Int, putCurrently: CurrentlyUpdateModel) {
        ApiClient.getRetrofit().putCurrently(id,putCurrently).enqueue(object :Callback<CurrentlyUpdateModel>{
            override fun onResponse(
                call: Call<CurrentlyUpdateModel>,
                response: Response<CurrentlyUpdateModel>
            ) {
                if (response.code()==200){
                    dismiss()
                    Toast.makeText(
                        binding.root.context,
                        "VAlyuta yangilandi",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            override fun onFailure(call: Call<CurrentlyUpdateModel>, t: Throwable) {
            }
        })
    }

    private fun postCategory(postCategory: CurrentePostModel) {
        ApiClient.getRetrofit().currencyPost(postCategory)
            .enqueue(object : Callback<CurrentePostModel> {
                override fun onResponse(
                    call: Call<CurrentePostModel>,
                    response: Response<CurrentePostModel>
                ) {
                    if (response.code() == 200) {
                        dismiss()
                        Log.e(TAG, "onResponse: start recycler123 ${response.body()}")
                    }
                }

                override fun onFailure(call: Call<CurrentePostModel>, t: Throwable) {
                    Toast.makeText(
                        binding.root.context,
                        "Category Qoshilmadi",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
    }
    private fun updateUi(){
        binding.currentlyNameTextSettingDialog.setText(marketCurr!!.currency)
        binding.currentlyPercentageTextSettingDialog.setText(marketCurr.price.toString())
    }

}