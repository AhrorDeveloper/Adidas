package com.axrorabdurayimjonov.adidas.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.axrorabdurayimjonov.adidas.OnClickItem.CategoryOnClick
import com.axrorabdurayimjonov.adidas.adapters.SettingGetCategoryAdapter
import com.axrorabdurayimjonov.adidas.api.ApiClient
import com.axrorabdurayimjonov.adidas.classes.CategoryDialog
import com.axrorabdurayimjonov.adidas.classes.CurrenceDialog
import com.axrorabdurayimjonov.adidas.databinding.FragmentCategoryBinding
import com.axrorabdurayimjonov.adidas.models.CategoryGetSettingModel
import com.axrorabdurayimjonov.adidas.models.SettingGetModelData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CategoryFragment : Fragment(),CategoryOnClick {
    private lateinit var binding: FragmentCategoryBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCategoryBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dialogCategoryOnClick()
        getCategorySetting()
    }

    private fun dialogCategoryOnClick() {
        binding.addCategory.setOnClickListener {
            val dialod = CategoryDialog(null)
            dialod.show(childFragmentManager,"CategoryDialog")
        }
    }

    private val TAG = "Category"
    private fun getCategorySetting() {
        ApiClient.getRetrofit().categoryGet(category_id = 0, page = 0, limit = 25)
            .enqueue(object :Callback<CategoryGetSettingModel>{
            override fun onResponse(
                call: Call<CategoryGetSettingModel>,
                response: Response<CategoryGetSettingModel>
            ) {
              if(response.code()==200){
                  val list =response.body()
                  list?.let {
                      val adapter = SettingGetCategoryAdapter(it.data,this@CategoryFragment)
                      binding.categoryRecyclerView.adapter = adapter
                  }
                  Log.e(TAG, "onResponse: start recycler123 ${response.body()}")
              }
            }
            override fun onFailure(call: Call<CategoryGetSettingModel>, t: Throwable) {
                Log.e("CategoryMealsViewModel",t.message.toString())
            }

        })
    }

    override fun updateOnClick(update1: SettingGetModelData) {
        val dialog = CategoryDialog(update1)
//        dialog.arguments= bundleOf(
//            "market" to update1
//        )
        dialog.show(childFragmentManager,"update")
    }
}