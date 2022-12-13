package com.edwinacubillos.librosapp.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edwinacubillos.librosapp.firebase.ResourceRemote
import com.edwinacubillos.librosapp.firebase.UserRepository
import emailValidator
import kotlinx.coroutines.launch
import passwordValidator

class LoginViewModel : ViewModel() {

    val userRepository = UserRepository()

    val datosValidos: MutableLiveData<Boolean> by lazy{
        MutableLiveData<Boolean>()
    }

    val errorMsg: MutableLiveData<String> by lazy{
        MutableLiveData<String>()
    }

    fun validarDatos(correo: String, password: String) {
        if (correo.isEmpty() || password.isEmpty())
            errorMsg.value = "Debe digitar todos los campos"
        else
            if (!emailValidator(correo)) {
                errorMsg.value = "El correo electrónico está mal escrito"
            } else
                if (!passwordValidator(password))
                    errorMsg.value = "La contraseña debe tener minimo 6 dígitos"
                else
                    viewModelScope.launch {
                        val result = userRepository.iniciarSesion(correo, password)
                        result.let{ resourceRemote ->
                            when (resourceRemote){
                                is ResourceRemote.Success -> {
                                    datosValidos.value = true
                                }
                                is ResourceRemote.Error -> {
                                    var msg = result.message
                                    when (result.message){
                                        "A network error (such as timeout, interrupted connection or unreachable host) has occurred." -> msg = "Revise su conexión de red"
                                        "The password is invalid or the user does not have a password." -> msg = "Correo y/o contraseña inválido"
                                    }
                                    errorMsg.postValue(msg)
                                }
                                else -> {
                                    //no usado
                                }
                            }
                        }
                    }




             //
    }
}