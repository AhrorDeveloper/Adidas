package com.axrorabdurayimjonov.adidas.classes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.axrorabdurayimjonov.adidas.api.ApiClient
import com.axrorabdurayimjonov.adidas.databinding.DialogProviderAddBinding
import com.axrorabdurayimjonov.adidas.models.ProviderDataModel
import com.axrorabdurayimjonov.adidas.models.ProviderPostAddModel
import com.axrorabdurayimjonov.adidas.models.UpdateProviderModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DialogProviderAdd : DialogFragment() {

    private lateinit var binding: DialogProviderAddBinding
    private var market: ProviderDataModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        market = arguments?.getSerializable("market") as ProviderDataModel?
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogProviderAddBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (market != null) {
            updateUI()
        }

        binding.providerExitDialog.setOnClickListener {
            dismiss()
        }

        binding.providerOkDialog.setOnClickListener {
            when {
                binding.providerNameDialog.text.isEmpty() -> binding.providerNameDialog.error =
                    "nomini kiriting"
                binding.providerAddressDialog.text.isEmpty() -> binding.providerAddressDialog.error =
                    "Manzilni kiriting"
                binding.providerPhoneDialog.text.isEmpty() -> binding.providerPhoneDialog.error =
                    "Telefon nomerni kiriting"
                else -> {
                    val username = binding.providerNameDialog.text.toString().trim()
                    val phone = binding.providerPhoneDialog.text.toString().toLong()
                    val address = binding.providerAddressDialog.text.toString().trim()



                    if (market == null) {
                        val postProvidermodel = ProviderPostAddModel(address, username, phone)
                        postProvider(postProvidermodel)

                    } else {
                        val putProvider = UpdateProviderModel(address,username,phone)
                        updateProvider(market!!.id, putProvider)
                    }
                }
            }
        }
    }

    private fun updateProvider(marketId: Int,putProvider: UpdateProviderModel) {
        ApiClient.getRetrofit().putProvider(marketId,putProvider).enqueue(object : Callback<UpdateProviderModel> {

            override fun onResponse(
                call: Call<UpdateProviderModel>,
                response: Response<UpdateProviderModel>
            ) {
                if (response.code()==200){
                    dismiss()
                    Toast.makeText(
                        binding.root.context,
                        "Taminotchi yangilandi",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            override fun onFailure(call: Call<UpdateProviderModel>, t: Throwable) {

            }
        })
    }

    private fun postProvider(postProvidermodel: ProviderPostAddModel) {
        ApiClient.getRetrofit().postProvider(postProvidermodel)
            .enqueue(object : Callback<ProviderPostAddModel> {
                override fun onResponse(
                    call: Call<ProviderPostAddModel>,
                    response: Response<ProviderPostAddModel>
                ) {
                    if (response.code() == 200) {
                        dismiss()
                        Toast.makeText(
                            binding.root.context,
                            "Taminotchi qoshildi",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<ProviderPostAddModel>, t: Throwable) {
                }
            })
    }

    private fun updateUI() {
        binding.providerNameDialog.setText(market!!.name)
        binding.providerAddressDialog.setText(market!!.address)
        binding.providerPhoneDialog.setText(market!!.phone)
    }
}