package com.axrorabdurayimjonov.adidas.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.axrorabdurayimjonov.adidas.databinding.FragmentProviderMoneyPayBinding
import com.axrorabdurayimjonov.adidas.fragment.WarehouseGetFragment
import com.axrorabdurayimjonov.adidas.fragment.WarehousePostFragment
import com.axrorabdurayimjonov.adidas.fragment.WarehouseWarehouseFragment

internal class WarehouseTabLayoutAdapter( fm:FragmentActivity):FragmentStateAdapter(fm){

    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
     return   when(position){
            0->{
                WarehouseWarehouseFragment()
            }
            1->{
                WarehouseGetFragment()
            }
            2->{
                WarehousePostFragment()
            }
            else ->WarehouseWarehouseFragment()
        }
    }
}
