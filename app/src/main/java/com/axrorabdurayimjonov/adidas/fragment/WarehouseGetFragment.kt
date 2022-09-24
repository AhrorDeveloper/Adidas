package com.axrorabdurayimjonov.adidas.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.axrorabdurayimjonov.adidas.R
import com.axrorabdurayimjonov.adidas.adapters.WarehouseGetTabLayoutAdapter
import com.axrorabdurayimjonov.adidas.databinding.FragmentWarehouseGetBinding
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_warehouse_get.*


class WarehouseGetFragment : Fragment() {
private lateinit var binding:FragmentWarehouseGetBinding
private lateinit var adapter:WarehouseGetTabLayoutAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWarehouseGetBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = WarehouseGetTabLayoutAdapter(requireActivity())
        binding.viewPager.adapter = adapter

        TabLayoutMediator(binding.tabLayout,binding.viewPager){tab,position->
            when(position){
                0->{
                    tab.text = getString(R.string.warehouseGetIsExpected)
                }
                1->{
                    tab.text = getString(R.string.warehouseGetAccepted)
                }
            }
        }.attach()
    }
}