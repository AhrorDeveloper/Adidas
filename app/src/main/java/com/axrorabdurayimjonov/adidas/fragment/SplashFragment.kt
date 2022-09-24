package com.axrorabdurayimjonov.adidas.fragment

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.navigation.fragment.findNavController
import com.axrorabdurayimjonov.adidas.R
import com.axrorabdurayimjonov.adidas.api.DataSource
import com.axrorabdurayimjonov.adidas.api.NetworkHelper
import com.axrorabdurayimjonov.adidas.databinding.FragmentSplashBinding

var token=""
class SplashFragment : Fragment() {
    lateinit var binding: FragmentSplashBinding
    private var txtAnim: Animation? = null
    private val dataSource by lazy { DataSource() }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSplashBinding.inflate(layoutInflater)
        token = dataSource.getToken(binding.root.context)
        txtAnim = AnimationUtils.loadAnimation(binding.root.context, R.anim.anim)
        binding.animText.animation = txtAnim
        Handler().postDelayed(
            {
                if (NetworkHelper.isNetworkConnected(binding.root.context)) {
                    checkAuth()
                } else {
                    showAlertDialog()
                }
            }, 2000
        )
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }
    private fun checkAuth() {
        if (dataSource.isLogin(binding.root.context)) {
            // loginApi ()
        } else {
             findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
        }
    }
    private fun showAlertDialog() {
        val alert = AlertDialog.Builder(
            binding.root.context
        )
        alert.setTitle("Xatolik")
        alert.setMessage("Internetga ulanganligizni tekshiring!!!")
        alert.setCancelable(false)
        alert.setPositiveButton("Qayta yuklash") { dialogInterface: DialogInterface, i: Int ->
            dialogInterface.dismiss()
            if (NetworkHelper.isNetworkConnected(binding.root.context)) {
                checkAuth()
            } else {
                alert.create().show()
            }
        }
        alert.setNegativeButton("Chiqish") { dialogInterface: DialogInterface, i: Int ->
            dialogInterface.dismiss()
            activity?.finish()
        }
        alert.create().show()
    }
}