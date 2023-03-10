package com.example.fragments.ui.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData


class MainViewModel(application: Application) : AndroidViewModel(application) {

    private var numero1: Int = numeroAleatorio()
    private var numero2: Int = numeroAleatorio()
    private var cuenta:String="$numero1 + $numero2"
    private var resulta: String = ""



    var liveResultado= MutableLiveData(resulta)
    var liveCuenta= MutableLiveData(cuenta)

    init {
        liveCuenta.value=cuenta
        liveResultado.value=resulta
    }


    /**
     * Funcion que escoge un numero aleatorio del 0 al 100
     */
    fun numeroAleatorio(): Int {

        return (0..100).random()
    }

    /**
     * Funcion que comprueba si el resultado dado por el usuario es correcto
     * Escribe el resultado en la casilla de texto donde se introduce la respuesta
     */

    fun comprobarResultado(resultado: Int) {

        val test = numero1 + numero2

        val correcto= test == resultado

        resulta= "$correcto"

        liveResultado.value = resulta
    }


    /**
     * Funcion que cambia la cuenta en el fragment de calculo
     */
    fun cambiarCuenta(){

        numero1=numeroAleatorio()
        numero2=numeroAleatorio()
        cuenta="$numero1 + $numero2"
        Log.d("NUMEROS", cuenta)

        liveCuenta.value = cuenta


    }


}