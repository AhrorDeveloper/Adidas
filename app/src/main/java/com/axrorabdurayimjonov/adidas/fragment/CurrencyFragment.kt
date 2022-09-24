package com.axrorabdurayimjonov.adidas.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.axrorabdurayimjonov.adidas.OnClickItem.CurrentlyOnClick
import com.axrorabdurayimjonov.adidas.adapters.CurrentlyAdapter
import com.axrorabdurayimjonov.adidas.api.ApiClient
import com.axrorabdurayimjonov.adidas.classes.CurrenceDialog
import com.axrorabdurayimjonov.adidas.databinding.FragmentCurrencyBinding
import com.axrorabdurayimjonov.adidas.models.CurrentlyGetModel
import com.axrorabdurayimjonov.adidas.models.CurrentlyGetModelItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CurrencyFragment : Fragment(),CurrentlyOnClick {
    private lateinit var binding:FragmentCurrencyBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = FragmentCurrencyBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialogCurrency()
        currentlyGet()
    }

    private fun currentlyGet() {
        ApiClient.getRetrofit().currentlyGet().enqueue(object :Callback<CurrentlyGetModel>{
            override fun onResponse(
                call: Call<CurrentlyGetModel>,
                response: Response<CurrentlyGetModel>
            ) {
                if (response.code()==200){
                    val list = response.body()
                    list?.let {
                        val adapter = CurrentlyAdapter(it,this@CurrencyFragment)
                        binding.currentlyRecyclerView.adapter=adapter
                    }
                }
            }
            override fun onFailure(call: Call<CurrentlyGetModel>, t: Throwable) {

            }
        })
    }

    private fun dialogCurrency() {
        binding.addCurrenty.setOnClickListener {
            val dialog = CurrenceDialog(null)
            dialog.show(childFragmentManager,"currently")
        }
    }

    override fun updateCurr(update: CurrentlyGetModelItem) {
        val dialog = CurrenceDialog(update)
//        dialog.arguments = bundleOf(
//            "currently" to update
//        )
        dialog.show(childFragmentManager,"update")
    }
}