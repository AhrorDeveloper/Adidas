package com.axrorabdurayimjonov.adidas.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.axrorabdurayimjonov.adidas.adapters.ProviderPayMoneyAdapter
import com.axrorabdurayimjonov.adidas.databinding.FragmentProviderMoneyPayBinding
import com.axrorabdurayimjonov.adidas.models.ProviderDataModel

class ProviderMoneyPayFragment : Fragment() {
    private lateinit var binding: FragmentProviderMoneyPayBinding
    private var model: ProviderDataModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
                model = it.getSerializable("provider_list") as ProviderDataModel
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProviderMoneyPayBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (model != null) {
            val adapter = ProviderPayMoneyAdapter(model!!.balances)
            binding.providerPayMoneyRecyclerView.adapter = adapter
        }
    }
}