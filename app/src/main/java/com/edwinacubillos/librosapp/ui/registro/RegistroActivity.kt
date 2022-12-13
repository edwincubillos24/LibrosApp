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

        val passwordObserver = Observer<Boolean> { esValido ->
            onBackPressedDispatcher.onBackPressed()
        }

        registroViewModel.passwordsValidos.observe(this, passwordObserver)

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
                val esAccion = accionCheckBox.isChecked
                val esAventura = aventuraCheckBox.isChecked
                val esFantasia = fantasiaCheckBox.isChecked
                val esRomance = romanceCheckBox.isChecked
                val esSuspenso = suspensoCheckBox.isChecked
                val esTerror = terrorCheckBox.isChecked

                registroViewModel.validarCampos(nombre, correo, password, repPassword)
            }
        }
    }
}