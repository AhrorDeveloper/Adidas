package com.axrorabdurayimjonov.adidas.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.axrorabdurayimjonov.adidas.OnClickItem.WarehousOnClick
import com.axrorabdurayimjonov.adidas.R
import com.axrorabdurayimjonov.adidas.adapters.WarehouseAdapter
import com.axrorabdurayimjonov.adidas.api.ApiClient
import com.axrorabdurayimjonov.adidas.databinding.FragmentWarehouseBinding
import com.axrorabdurayimjonov.adidas.models.WarehouseModel
import kotlinx.android.synthetic.main.fragment_pvovide.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WarehouseFragment : Fragment(),WarehousOnClick {
    private lateinit var binding: FragmentWarehouseBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentWarehouseBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        backButton.setOnClickListener {
            findNavController().popBackStack()
        }
        warehouseget()
    }
val TAG = "Warehouse"
    private fun warehouseget() {
        ApiClient.getRetrofit().warehouseget().enqueue(object : Callback<List<WarehouseModel>>{
            override fun onResponse(
                call: Call<List<WarehouseModel>>,
                response: Response<List<WarehouseModel>>
            ) {
                if (response.code() ==200){
                    val list = response.body()
                    list?.let {
                        val adapter = WarehouseAdapter(it,this@WarehouseFragment)
                        binding.warehouseRecycler.adapter = adapter
                    }
                    Log.e(TAG, "Warehouse ${response.body()}")
                }
            }
            override fun onFailure(call: Call<List<WarehouseModel>>, t: Throwable) {
                Log.e("Warehouse",t.message.toString())
            }
        })
    }

    override fun wareOcClick(onClickOpen: WarehouseModel) {
       findNavController().navigate(R.id.action_warehouseFragment_to_wareHouseInnovationFragment)
    }
}