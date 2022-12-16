package com.edwinacubillos.librosapp.ui.registro

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RegistroViewModel : ViewModel() {

    val passwordsValidos: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    val errorMsg: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    fun validarCampos(nombre: String, correo: String, password: String, repPassword: String) {
        if (nombre.isEmpty() || correo.isEmpty() || password.isEmpty() || repPassword.isEmpty())
            errorMsg.value = "Debe digitar todos los campos"
        else {
            if (password == repPassword)
                passwordsValidos.value = true
            else
                errorMsg.value = "Las contraseñas deben de ser iguales"
        }
    }
}