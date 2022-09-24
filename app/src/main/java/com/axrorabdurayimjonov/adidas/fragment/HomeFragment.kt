package com.axrorabdurayimjonov.adidas.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.os.bundleOf
import androidx.core.view.GravityCompat
import androidx.navigation.fragment.findNavController
import com.axrorabdurayimjonov.adidas.OnClickItem.BranchOnClick
import com.axrorabdurayimjonov.adidas.R
import com.axrorabdurayimjonov.adidas.adapters.BranchAdapter
import com.axrorabdurayimjonov.adidas.api.ApiClient
import com.axrorabdurayimjonov.adidas.classes.BranchUpdate
import com.axrorabdurayimjonov.adidas.classes.DialogBranchsAdd
import com.axrorabdurayimjonov.adidas.databinding.FragmentHomeBinding
import com.axrorabdurayimjonov.adidas.models.BranchModel
import com.axrorabdurayimjonov.adidas.models.BranchModel1
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment(),BranchOnClick {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var toggle:ActionBarDrawerToggle
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
      binding = FragmentHomeBinding.inflate(inflater,container,false)
        binding.apply {
            toggle = ActionBarDrawerToggle(requireActivity(),drawer,R.string.open,R.string.close)
            drawer.addDrawerListener(toggle)
            toggle.syncState()
        }
        binding.navView.itemIconTintList = null
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        drawerMenuImg.setOnClickListener {
            drawer.openDrawer(GravityCompat.START)
        }
        getBranchApi()
        dialogOnClick()
      nav_view.setNavigationItemSelectedListener {
          binding.drawer.closeDrawer(GravityCompat.START)
            when(it.itemId){
                R.id.warehouse_fragment_item->{
                    findNavController().navigate(R.id.action_homeFragment_to_warehouseFragment)
                }
                R.id.setting_fragment_item->{
                    findNavController().navigate(R.id.action_homeFragment_to_settingsFragment)
                }
                R.id.provides_fragment_item->{
                    findNavController().navigate(R.id.action_homeFragment_to_pvovideFragment)
                }
                R.id.branch_fragment_item->{
                    findNavController().navigate(R.id.homeFragment)
                }
               R.id.providers_fragment_item->{
                 findNavController().navigate(R.id.action_homeFragment_to_providerFragment)
            }
            }
          true
        }
    }
    private fun dialogOnClick() {
        binding.branchAdd.setOnClickListener {
            val dialog = DialogBranchsAdd()
            dialog.show(childFragmentManager,"branchDialog")
        }
    }
    private val TAG = "HomeFragment"
    private fun getBranchApi(){
        ApiClient.getRetrofit().branchGet().enqueue(object : Callback<BranchModel>{
            override fun onResponse(
                call: Call<BranchModel>,
                response: Response<BranchModel>
            ) {
                Log.e("TAG", "BranchGet: " + response.code())
              if (response.code() == 200){
                  val list = response.body()
                   list?.let {newList ->
                      val adapter = BranchAdapter(newList, this@HomeFragment)
                       binding.branchRv.adapter = adapter
                   }
                  Log.e(TAG, "onResponse: start recycler123 ${response.body()}")
              }
            }
            override fun onFailure(call: Call<BranchModel>, t: Throwable) {
                Log.e("BranchModel",t.message.toString())
            }
        })
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
    override fun branchWorkerClick(model: BranchModel1) {
        findNavController().navigate(R.id.action_homeFragment_to_employeesFragment, bundleOf(Pair("model",model)))
    }

    override fun branchCall(call: BranchModel1) {
        startActivity(Intent(Intent.ACTION_DIAL).setData(Uri.parse("tel:${call.phone}")))

    }

    override fun branchUpdate(update: BranchModel1) {
        val dialog = BranchUpdate()
        dialog.show(childFragmentManager,"update")
    }
}


