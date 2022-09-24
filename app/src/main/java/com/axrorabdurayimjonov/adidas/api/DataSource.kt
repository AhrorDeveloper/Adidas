package com.axrorabdurayimjonov.adidas.api

import android.content.Context
import android.content.SharedPreferences
import android.media.MediaCodec.MetricsConstants.MODE
import android.util.Log

class DataSource {
    private lateinit var sharedPreferences: SharedPreferences

    fun isLogin(context: Context):Boolean{
        sharedPreferences = context.getSharedPreferences(KEY_REGISTER,MODE)
        return sharedPreferences.getBoolean("register",false)
    }
    fun saveLogin(context: Context,token:String){
        Log.e("TAG", "saveToken: $token")
        sharedPreferences=context.getSharedPreferences(KEY_TOKEN, MODE)
        val editor = sharedPreferences.edit()
        editor?.putString("forToken",token)?.apply()
    }
    fun getToken(context: Context):String{
        sharedPreferences = context.getSharedPreferences(KEY_TOKEN, MODE)
        val token = sharedPreferences.getString("forToken","")
        return token!!
    }

    companion object {
        private const val KEY_LOGIN = "login"
        private const val KEY_TOKEN = "token"
        private const val KEY_WAREHOUSE = "ombor_id"
        private const val KEY_REGISTER = "register"
        private const val MODE = Context.MODE_PRIVATE
    }
}