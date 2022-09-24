package com.axrorabdurayimjonov.adidas.fragment

import android.content.ContentValues.TAG
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.axrorabdurayimjonov.adidas.OnClickItem.ProviderOnClick
import com.axrorabdurayimjonov.adidas.R
import com.axrorabdurayimjonov.adidas.adapters.ProviderAdapter
import com.axrorabdurayimjonov.adidas.api.ApiClient
import com.axrorabdurayimjonov.adidas.classes.DialogProviderAdd
import com.axrorabdurayimjonov.adidas.databinding.FragmentProviderBinding
import com.axrorabdurayimjonov.adidas.models.ProviderDataModel
import com.axrorabdurayimjonov.adidas.models.ProviderModel
import kotlinx.android.synthetic.main.fragment_pvovide.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProviderFragment : Fragment(),ProviderOnClick {
    private lateinit var binding: FragmentProviderBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       binding = FragmentProviderBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        backButton.setOnClickListener {
            findNavController().popBackStack()
        }
        providerGetApi()
        dialogProvider()
    }
    private fun dialogProvider() {
        binding.addProvider.setOnClickListener {
            val dialog = DialogProviderAdd()
            dialog.show(childFragmentManager,"dialog provider")
        }
    }
    private fun providerGetApi() {
        ApiClient.getRetrofit().providerGet( page = 0, limit = 50).enqueue(object :Callback<ProviderModel>{
            override fun onResponse(call: Call<ProviderModel>,
                                    response: Response<ProviderModel>) {
               if (response.code()==200){
                   val list = response.body()
                       val adapter = ProviderAdapter(list!!.data, this@ProviderFragment)
                       binding.providerRecycler.adapter = adapter
                   Log.e(TAG, "Provider ${response.body()}")
               }
            }
            override fun onFailure(call: Call<ProviderModel>, t: Throwable) {
                Log.e("Provider",t.message.toString())
            }
        })
    }  override fun providerMoney(pay: ProviderDataModel) {
        val bundle = Bundle()
        bundle.putSerializable("provider_list", pay)
        findNavController().navigate(R.id.action_providerFragment_to_providerMoneyPayFragment, bundle)
    }

    override fun providerUpdate(update: ProviderDataModel) {
        val dialog = DialogProviderAdd()
        dialog.arguments = bundleOf(
            "market" to update
        )
        dialog.show(childFragmentManager, "update")
    }

    override fun providerCall(call: ProviderDataModel) {
        startActivity(Intent(Intent.ACTION_DIAL).setData(Uri.parse("tel:${call.phone}")))
    }
}