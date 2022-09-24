package com.axrorabdurayimjonov.adidas.classes

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.axrorabdurayimjonov.adidas.api.ApiClient
import com.axrorabdurayimjonov.adidas.databinding.FragmentDialogBranchesAddBinding
import com.axrorabdurayimjonov.adidas.models.PostBranchModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class   DialogBranchsAdd:DialogFragment(){
    private lateinit var binding:FragmentDialogBranchesAddBinding
    companion object{
        val IMAGE_REQUEST_CODE = 100
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_Holo_Light_Dialog_NoActionBar_MinWidth)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDialogBranchesAddBinding.inflate(layoutInflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.branchExitDialog.setOnClickListener {
            dismiss()
        }
        binding.branchOkDialog.setOnClickListener {
            when{
                binding.branchNameAddDialog.text.isEmpty()->binding.branchNameAddDialog.error = "ISMNI KIRITING"
                binding.branchPhoneNumberDialog.text.isEmpty()->binding.branchPhoneNumberDialog.error = "Nomerni kiriting"
                binding.branchAddressDialog.text.isEmpty()->binding.branchAddressDialog.error = "Manzilni kiriting"
                binding.branchAddressLatDialog.text.isEmpty()->binding.branchAddressLatDialog.error = "Latinutni kiriting"
                binding.branchAddressLongDialog.text.isEmpty()->binding.branchAddressLongDialog.error = "Longtitut kiriting"
                else->{
                   val username = binding.branchNameAddDialog.text.toString().trim()
                   val address = binding.branchAddressDialog.text.toString().trim()
                   val map_long = binding.branchAddressLongDialog.text.toString().trim()
                   val map_lat = binding.branchAddressLatDialog.text.toString().trim()
                   val phone = binding.branchPhoneNumberDialog.text.toString().toInt()

                    val postBranchModel = PostBranchModel(address,"","",map_lat,map_long,username,phone)
                    postBranch(postBranchModel)
                }
            }
        }
    }
    private fun postBranch( postBranchModel: PostBranchModel ) {
        ApiClient.getRetrofit().postBranch(postBranchModel).enqueue(object :Callback<PostBranchModel>{
            override fun onResponse(
                call: Call<PostBranchModel>,
                response: Response<PostBranchModel>
            ) {
                if (response.code()==200){
                    dismiss()
                    Toast.makeText(
                        binding.root.context,
                        "Fillial qoshildi",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            override fun onFailure(call: Call<PostBranchModel>, t: Throwable) {
            }
        })
    }
}