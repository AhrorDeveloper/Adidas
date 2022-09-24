package com.axrorabdurayimjonov.adidas.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.axrorabdurayimjonov.adidas.OnClickItem.EmployeOnClick
import com.axrorabdurayimjonov.adidas.adapters.EmployeesAdapter
import com.axrorabdurayimjonov.adidas.api.ApiClient
import com.axrorabdurayimjonov.adidas.classes.DialogEmployeeAdd
import com.axrorabdurayimjonov.adidas.databinding.FragmentEmployeesBinding
import com.axrorabdurayimjonov.adidas.models.BranchModel
import com.axrorabdurayimjonov.adidas.models.BranchModel1
import com.axrorabdurayimjonov.adidas.models.EmployeesDataModel
import com.axrorabdurayimjonov.adidas.models.EmployeesWorkerModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EmployeesFragment : Fragment(),EmployeOnClick {
    private lateinit var binding:FragmentEmployeesBinding
    private var model: BranchModel1?=null
    private val TAG = "EmployeesFragment"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            model = it.getSerializable("model") as BranchModel1
        }
        Log.d(TAG, "onCreate: $model")
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=FragmentEmployeesBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addEmployee.imageTintMode = null
        binding.employeeImage.setOnClickListener {
            findNavController().popBackStack()
        }
        if (model != null) {
            employeesgetworker()
            dialogEmployee()
        }
    }
    private fun dialogEmployee() {
        binding.addEmployee.setOnClickListener {
            val dialod = DialogEmployeeAdd(model!!.id)
            dialod.show(childFragmentManager,"DialogEmployeeAdd")
        }
    }
    private fun employeesgetworker() {
        ApiClient.getRetrofit().employeesgetWorkers(branchId = model!!.id, page = 0, limit = 10)
            .enqueue(object :Callback<EmployeesWorkerModel>{
            override fun onResponse(
                call: Call<EmployeesWorkerModel>,
                response: Response<EmployeesWorkerModel>
            ) {
                if (response.code()==200){

                    val lisr = response.body()
                    lisr?.let {
                        val adapter = EmployeesAdapter(it.data,this@EmployeesFragment)
                        binding.employeeRecyclerView.adapter = adapter
                    }
                    Log.e(TAG, "EmployeesWorker ${response.body()}")
                }
            }
            override fun onFailure(call: Call<EmployeesWorkerModel>, t: Throwable) {
                Log.e("EmployeesWorker",t.message.toString())
            }
        })
    }

    override fun EmployeeCall(call: EmployeesDataModel) {
        startActivity(Intent(Intent.ACTION_DIAL).setData(Uri.parse("tel: ${call.phone}")))
    }
}