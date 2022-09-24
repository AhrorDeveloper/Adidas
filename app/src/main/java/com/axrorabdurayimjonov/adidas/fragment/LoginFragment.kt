package com.axrorabdurayimjonov.adidas.fragment

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.axrorabdurayimjonov.adidas.R
import com.axrorabdurayimjonov.adidas.api.ApiClient
import com.axrorabdurayimjonov.adidas.api.DataSource
import com.axrorabdurayimjonov.adidas.databinding.FragmentLoginBinding
import com.axrorabdurayimjonov.adidas.models.LoginModel
import kotlinx.android.synthetic.main.fragment_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var dataSource: DataSource
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataSource = DataSource()
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        token = DataSource().getToken(binding.root.context)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_enter.setOnClickListener {
            val loginname = binding.login.text.toString().trim()
            val password = binding.passwordd.text.toString().trim()
            if (loginname.isEmpty()) {
                login.error = "Loginni kiriting"
                login.requestFocus()
            }
            if (password.isEmpty()) {
                passwordd.error = "Parolni kiriting"
                passwordd.requestFocus()
            }
            loginUser(loginname, password)
            Log.e(ContentValues.TAG, "onViewCreated: $loginname, $password")
        }
    }
    private fun loginUser(username: String, password: String) {
        ApiClient.getRetrofit().checkUser(username, password)
            .enqueue(object : Callback<LoginModel> {
                override fun onResponse(call: Call<LoginModel>, response: Response<LoginModel>) {
                    val list = response.body()
                    Log.e("TAG", "LoginResponseCode: " + response.code())
                    if (response.code() == 200 && response.body() != null && response.body()?.role == "admin") {
                        dataSource.saveLogin(binding.root.context, list!!.access_token)
                        token = dataSource.getToken(binding.root.context)
                        findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                    } else {
                        Toast.makeText(
                            binding.root.context,
                            "Sizning login  yoki parolingiz noto'g'ri\nQayta urinib ko'ring!!!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
                override fun onFailure(call: Call<LoginModel>, t: Throwable) {
                    Log.e("TAG", "onFailure1: " + t.localizedMessage)
                    Toast.makeText(binding.root.context, "Xatolik :(", Toast.LENGTH_SHORT).show()
                }
            })
    }
}