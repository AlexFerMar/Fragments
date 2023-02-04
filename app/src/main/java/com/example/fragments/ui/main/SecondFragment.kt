package com.example.fragments.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.fragments.R

class SecondFragment : Fragment(), View.OnClickListener {

    companion object {
        fun newInstance() = SecondFragment()
    }

    private lateinit var btComprobar: Button
    private lateinit var etResultado: EditText
    private lateinit var tvCuenta: TextView



    private lateinit var viewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)


        //Observacion del cambio de Cuenta
        viewModel.liveCuenta.observe(
            this,
            Observer(
                fun(cuenta: String) {
                    tvCuenta.text = cuenta
                }
            )
        )



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        var view =inflater.inflate(R.layout.fragment_second, container, false)

        etResultado=view.findViewById(R.id.etResultado)

        tvCuenta=view.findViewById(R.id.tvCuenta)
        tvCuenta.text="0 + 0"

        btComprobar = view.findViewById(R.id.btComprobar)
        btComprobar.setOnClickListener(this)


        return view
    }

    override fun onClick(p0: View?) {

        val resultado = etResultado.text.toString()

        viewModel.comprobarResultado(resultado.toInt())


    }


}