package com.edwinacubillos.librosapp.ui.registro

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
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
            val intent = Intent()
            intent.putExtra("correo", correo)
            intent.putExtra("password", password)
            setResult(Activity.RESULT_OK, intent)
            finish()
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

                Log.d("datos", nombre + correo + password + genero + esTerror)

                registroViewModel.validarCampos(nombre, correo, password, repPassword)
            }
        }
    }
}