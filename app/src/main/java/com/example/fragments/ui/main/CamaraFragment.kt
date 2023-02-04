package com.example.fragments.ui.main

import android.graphics.Bitmap
import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.fragments.R

class CamaraFragment : Fragment() {

    companion object {
        fun newInstance() = CamaraFragment()
    }


    private lateinit var ivFoto: ImageView


    private lateinit var viewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        var view = inflater.inflate(R.layout.fragment_main, container, false)
        ivFoto = view.findViewById(R.id.ivFoto)

        return view
    }


    fun changePhoto(photo: Bitmap?) {

        ivFoto.setImageBitmap(photo)

    }


}