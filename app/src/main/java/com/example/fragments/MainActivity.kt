package com.example.fragments

import android.content.Intent
import android.content.res.Configuration
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.fragments.ui.main.CamaraFragment
import com.example.fragments.ui.main.MainViewModel
import com.example.fragments.ui.main.SumaFragment

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val CAMARA = 2
    private lateinit var btCalculo: Button
    private lateinit var btCamara: Button

    private val fragment1: CamaraFragment = CamaraFragment.newInstance()
    private val fragment2: SumaFragment = SumaFragment.newInstance()
    private var isLandsacpe = false


    private lateinit var viewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]


        val orientation = this.resources.configuration.orientation

        if (orientation === Configuration.ORIENTATION_LANDSCAPE) {

            landscape()


        } else if (orientation === Configuration.ORIENTATION_PORTRAIT) {

            portrait()

        }

        btCalculo = findViewById(R.id.btCalculadora)
        btCalculo.setOnClickListener(this)


        btCamara = findViewById(R.id.btCamara)
        btCamara.setOnClickListener(this)


    }

    /**
     * Funcion encargada de recoger el result de la activity camara
     */
    @Deprecated("Deprecated a partir de API 30")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        //Comprueba que la activiti haya terminado bien
        if (resultCode != RESULT_OK) return

        //Comprueba que la data del intent no sea nula
        if (data != null) {
            when (requestCode) {
                //Si el request code devuelto es el de la camara se ejecuta este bloque de codigo
                CAMARA -> {
                    val photo = data!!.extras!!["data"] as Bitmap?
                    fragment1.changePhoto(photo)

                }


            }
        }
    }

    /**
     * Funcion con las acciones que se activan cuando el telefono esta en horizontal
     */
    fun landscape() {

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        setContentView(R.layout.activity_main_landscape)

        fragmentTransaction.add(R.id.fContenedorMain, fragment1)
        fragmentTransaction.add(R.id.fContenedorSecond, fragment2)
        fragmentTransaction.commit()

        isLandsacpe = true

    }

    /**
     * Funcion con las acciones que se activan cuando el telefono esta en vertical
     */
    fun portrait() {

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        setContentView(R.layout.activity_main)

        fragmentTransaction.add(R.id.fContenedorMain, fragment1)
        fragmentTransaction.commit()

        isLandsacpe = false


    }


    /**
     * Definimos los clickListener de los botones, dependiendo del id de cada boton el codigo se ejecutara de una forma u otra
     */
    override fun onClick(view: View?) {
        when (view?.id) {

            btCamara.id -> {
                //Llamamos el intent de la camara
                val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(cameraIntent, CAMARA)
                if (!isLandsacpe) {

                    val fragmentTransaction = supportFragmentManager.beginTransaction()
                    fragmentTransaction.replace(R.id.fContenedorMain, fragment1)
                    fragmentTransaction.commit()
                }

            }


            btCalculo.id -> {
                //Llamamos el intent de la calculadora
                viewModel.cambiarCuenta()

                if (!isLandsacpe) {

                    val fragmentTransaction = supportFragmentManager.beginTransaction()
                    fragmentTransaction.replace(R.id.fContenedorMain, fragment2)
                    fragmentTransaction.commit()
                }

            }


        }


    }


}