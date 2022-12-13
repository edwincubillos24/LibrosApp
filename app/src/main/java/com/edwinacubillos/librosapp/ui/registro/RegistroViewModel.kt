package com.edwinacubillos.librosapp.ui.registro

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edwinacubillos.librosapp.firebase.ResourceRemote
import com.edwinacubillos.librosapp.firebase.UserRepository
import emailValidator
import kotlinx.coroutines.launch
import passwordValidator

class RegistroViewModel : ViewModel() {

    val userRepository = UserRepository()

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
            if (!emailValidator(correo)) {
                errorMsg.value = "El correo electrónico está mal escrito"
            } else
                if (!passwordValidator(password))
                    errorMsg.value = "La contraseña debe tener minimo 6 dígitos"
                else
                    if (password != repPassword)
                        errorMsg.value = "Las contraseñas deben de ser iguales"
                    else
                        viewModelScope.launch {
                            val result = userRepository.registrarUsuario(correo, password)
                            result.let { resourceRemote ->
                                when (resourceRemote) {
                                    is ResourceRemote.Success -> {
                                        passwordsValidos.postValue(true)
                                    }
                                    is ResourceRemote.Error -> {
                                        var msg = result.message
                                        when (result.message) {
                                            "The email address is already in use by another account." -> msg = "Ya existe una cuenta con ese correo electrónico"
                                            "A network error (such as timeout, interrupted connection or unreachable host) has occurred." -> msg = "Revise su conexión de red"
                                        }
                                        errorMsg.postValue(msg)
                                    }
                                    else -> {
                                        //no usado
                                    }
                                }
                            }
                        }
        }
    }
}