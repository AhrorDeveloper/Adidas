package com.axrorabdurayimjonov.adidas.classes

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.axrorabdurayimjonov.adidas.classes.DialogBranchsAdd.Companion.IMAGE_REQUEST_CODE
import com.axrorabdurayimjonov.adidas.databinding.BranchUpdateDialogBinding

class BranchUpdate :DialogFragment(){
    private lateinit var binding:BranchUpdateDialogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_Holo_Light_Dialog_NoActionBar_MinWidth)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BranchUpdateDialogBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imageButtonUpdate.setOnClickListener {
            imageGet()
        }
    }

    private fun imageGet() {
        val intent = Intent(Intent.ACTION_PICK)
       startActivityForResult(intent, IMAGE_REQUEST_CODE)
    }
        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode== IMAGE_REQUEST_CODE){
            binding.imageViewUpdate.setImageURI(data?.data)
        }

    }
}