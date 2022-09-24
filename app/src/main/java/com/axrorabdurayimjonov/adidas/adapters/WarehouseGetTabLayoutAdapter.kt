package com.axrorabdurayimjonov.adidas.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.axrorabdurayimjonov.adidas.fragment.WarehouseFragmentGetAccepted
import com.axrorabdurayimjonov.adidas.fragment.WarehouseFragmentGetIsExpected

internal class WarehouseGetTabLayoutAdapter(fm : FragmentActivity):FragmentStateAdapter(fm) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0->{
                WarehouseFragmentGetIsExpected()
            }
            1->{
                WarehouseFragmentGetAccepted()
            }
            else->WarehouseFragmentGetIsExpected()
        }
    }
}