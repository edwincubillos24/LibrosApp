package com.edwinacubillos.librosapp.ui.registro

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.edwinacubillos.librosapp.databinding.ActivityRegistroBinding

class RegistroActivity : AppCompatActivity() {

    private lateinit var registroBinding: ActivityRegistroBinding
    private lateinit var registroViewModel: RegistroViewModel
    private var password = ""
    private var correo = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registroBinding = ActivityRegistroBinding.inflate(layoutInflater)
        val view = registroBinding.root

        registroViewModel = ViewModelProvider(this)[RegistroViewModel::class.java]

        setContentView(view)

        val creacionUsuarioExitosaObserver = Observer<Boolean> { esValido ->
            onBackPressedDispatcher.onBackPressed()
        }

        registroViewModel.creacionUsuarioExitosa.observe(this, creacionUsuarioExitosaObserver)

        val errorMsgObserver = Observer<String> { errorMsg ->
            Toast.makeText(this, errorMsg, Toast.LENGTH_SHORT).show()
        }

        registroViewModel.errorMsg.observe(this, errorMsgObserver)


        with(registroBinding) {
            registrarButton.setOnClickListener {
                val nombre = nombreEditText.text.toString()
                correo = correoEditText.text.toString()
                password = passwordEditText.text.toString()
                val repPassword = repPasswordEditText.text.toString()
                val genero = if (masculinoRadioButton.isChecked)
                    "Masculino"
                else
                    "Femenino"
                var generosFavoritos = ""
                if (accionCheckBox.isChecked) generosFavoritos = "Acción "
                if (aventuraCheckBox.isChecked) generosFavoritos = generosFavoritos + "Aventura "
                if (fantasiaCheckBox.isChecked) generosFavoritos = generosFavoritos + "Fantasia "
                if (romanceCheckBox.isChecked) generosFavoritos = generosFavoritos + "Romance "
                if (suspensoCheckBox.isChecked) generosFavoritos = generosFavoritos + "Suspenso"
                if (terrorCheckBox.isChecked) generosFavoritos = generosFavoritos + "Terror"

                registroViewModel.validarCampos(nombre, correo, password, repPassword, genero, generosFavoritos)
            }
        }
    }
}