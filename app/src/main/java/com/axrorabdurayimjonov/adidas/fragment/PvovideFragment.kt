package com.axrorabdurayimjonov.adidas.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.axrorabdurayimjonov.adidas.R
import com.axrorabdurayimjonov.adidas.adapters.ProvideTabLayoutAdapter
import com.axrorabdurayimjonov.adidas.databinding.FragmentPvovideBinding
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_pvovide.*


class PvovideFragment : Fragment() {
    private lateinit var binding: FragmentPvovideBinding
    private lateinit var adapter:ProvideTabLayoutAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPvovideBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        backButton.setOnClickListener {
            findNavController().popBackStack()
        }
        adapter = ProvideTabLayoutAdapter(requireActivity())
        binding.viewPager.adapter = adapter

        TabLayoutMediator(binding.tabLayout,binding.viewPager){tab,position->
            when(position){
                0->{
                    tab.text = getString(R.string.providerTabLayoutActive)
                }
                1->{
                    tab.text = getString((R.string.provideTabLayoutCompleted))
                }
            }
        }.attach()
    }
}