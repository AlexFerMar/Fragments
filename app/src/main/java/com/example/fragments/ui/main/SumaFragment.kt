package com.example.fragments.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.fragments.R

class SumaFragment : Fragment(), View.OnClickListener {

    companion object {
        fun newInstance() = SumaFragment()
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
                    val tvCuenta = view?.findViewById<TextView>(R.id.tvCuenta)
                    tvCuenta?.setText(cuenta)
                }
            )
        )

        //Observacion del cambio de Resultado
        viewModel.liveResultado.observe(
            this,
            Observer(
                fun(resultado: String) {

                    val etResultado = view?.findViewById<TextView>(R.id.etResultado)
                    etResultado?.setText(resultado)

                    viewModel.cambiarCuenta()

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


    /**
     * Definimos los clickListener del boton
     */
    override fun onClick(p0: View?) {

        val resultado = etResultado.text.toString()


        try {
            viewModel.comprobarResultado(resultado.toInt())
        }catch (e :Exception){
            //En caso de que el texto no pueda ser casteado salta este trozo de codigo y devuelve el valor como -1
            //Como es la suma de dos numeros positivos esto hace que lo de abajo sea equivalente a "false" cuando se compruebe el resultado
            viewModel.comprobarResultado(-1)

        }




    }


}