package com.example.fragments.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData


class MainViewModel(application: Application) : AndroidViewModel(application) {

    private var numero1: Int = numeroAleatorio()
    private var numero2: Int = numeroAleatorio()
    private var cuenta:String="$numero1 + $numero2"
    private var resulta: String = "Opera para comprobar"



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
     * Devuelve un boolean indicando si ha acertado o no
     */

    fun comprobarResultado(resultado: Int) {

        val test = numero1 + numero2

        val correcto= test == resultado

        resulta= "$correcto"

        liveResultado.value = resulta
    }

    fun cambiarCuenta(){

        numero1=numeroAleatorio()
        numero2=numeroAleatorio()
        cuenta="$numero1 + $numero2"

        liveCuenta.setValue(cuenta)


    }


}