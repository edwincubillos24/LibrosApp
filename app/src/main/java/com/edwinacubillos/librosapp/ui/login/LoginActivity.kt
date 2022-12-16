package com.edwinacubillos.librosapp.ui.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.edwinacubillos.librosapp.databinding.ActivityLoginBinding
import com.edwinacubillos.librosapp.ui.main.MainActivity
import com.edwinacubillos.librosapp.ui.registro.RegistroActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var loginBinding: ActivityLoginBinding
    private lateinit var loginViewModel: LoginViewModel
    private var correoRegistrado = ""
    private var passwordRegistrado = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        val view = loginBinding.root

        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        setContentView(view)

        val datosObserver = Observer<Boolean> {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        loginViewModel.datosValidos.observe(this, datosObserver)

        val errorMsgObserver = Observer<String> { errorMsg ->
            Toast.makeText(this, errorMsg, Toast.LENGTH_SHORT).show()
        }

        loginViewModel.errorMsg.observe(this, errorMsgObserver)

        with(loginBinding) {
            loginButton.setOnClickListener {
                val correo = correoEditText.text.toString()
                val password = passwordEditText.text.toString()

                loginViewModel.validarDatos(correo, correoRegistrado, password, passwordRegistrado)
            }

            registrarButton.setOnClickListener {
                startForResult.launch(Intent(this@LoginActivity, RegistroActivity::class.java))
            }
        }
    }

    val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val intent = result.data
            correoRegistrado = intent!!.extras!!.getString("correo").toString()
            passwordRegistrado = intent.extras!!.getString("password").toString()
        }
    }

}



