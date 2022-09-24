package com.axrorabdurayimjonov.adidas.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.axrorabdurayimjonov.adidas.R
import com.axrorabdurayimjonov.adidas.databinding.FragmentSettingsBinding
import kotlinx.android.synthetic.main.fragment_pvovide.*


class SettingsFragment : Fragment() {
    private lateinit var binding: FragmentSettingsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingsBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.settingCategoryBtn.setOnClickListener {
            findNavController().navigate(R.id.action_settingsFragment_to_categoryFragment)
        }
        binding.settingValyutaBtn.setOnClickListener {
            findNavController().navigate(R.id.action_settingsFragment_to_currencyFragment)
        }

        backButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}