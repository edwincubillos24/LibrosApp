package com.edwinacubillos.librosapp.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    val datosValidos: MutableLiveData<Boolean> by lazy{
        MutableLiveData<Boolean>()
    }

    val errorMsg: MutableLiveData<String> by lazy{
        MutableLiveData<String>()
    }

    fun validarDatos(correo: String, correoRegistrado: String, password: String, passwordRegistrado: String) {
        if (correo.isEmpty() || password.isEmpty())
            errorMsg.value = "Debe digitar todos los campos"
        else
            if (correo == correoRegistrado && password == passwordRegistrado)
                datosValidos.value = true
            else
                errorMsg.value = "El correo y/o contraseña son inválidos"
    }
}