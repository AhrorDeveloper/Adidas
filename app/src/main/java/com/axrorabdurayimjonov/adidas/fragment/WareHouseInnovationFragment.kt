package com.axrorabdurayimjonov.adidas.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager.widget.ViewPager
import com.axrorabdurayimjonov.adidas.R
import com.axrorabdurayimjonov.adidas.adapters.WarehouseTabLayoutAdapter
import com.axrorabdurayimjonov.adidas.databinding.FragmentWareHouseInnovationBinding
import com.axrorabdurayimjonov.adidas.models.WarehouseModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class WareHouseInnovationFragment : Fragment() {
    private lateinit var binding: FragmentWareHouseInnovationBinding
    private lateinit var adapter:WarehouseTabLayoutAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWareHouseInnovationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = WarehouseTabLayoutAdapter(requireActivity())
        binding.viewPager.adapter = adapter

        TabLayoutMediator(binding.tabLayout,binding.viewPager){ tab,position->

            when(position){
                0->{
                    tab.text = getString(R.string.warehouse)
                }
                1->{
                    tab.text = getString(R.string.warehousepost)
                }
                2->{
                     tab.text = getString(R.string.warehouseget)
                }
            }
        }.attach()

    }
}