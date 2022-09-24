package com.axrorabdurayimjonov.adidas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.axrorabdurayimjonov.adidas.databinding.ActivityMainBinding
import com.axrorabdurayimjonov.adidas.fragment.HomeFragment

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



    }
}