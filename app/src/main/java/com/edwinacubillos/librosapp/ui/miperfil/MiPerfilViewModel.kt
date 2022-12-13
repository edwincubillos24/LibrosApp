package com.edwinacubillos.librosapp.ui.miperfil

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edwinacubillos.librosapp.firebase.ResourceRemote
import com.edwinacubillos.librosapp.firebase.UserRepository
import com.edwinacubillos.librosapp.firebase.model.Usuario
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

class MiPerfilViewModel : ViewModel() {

    val userRepository = UserRepository()

    val usuarioCargado: MutableLiveData<Usuario> by lazy {
        MutableLiveData<Usuario>()
    }

    val sesionCerrada: MutableLiveData<Boolean> by lazy{
        MutableLiveData<Boolean>()
    }

    val errorMsg: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    fun cargarUsuario() {
        viewModelScope.launch {
            val result = userRepository.cargarUsuario()
            result.let { resourceRemote ->
                when (resourceRemote){
                    is ResourceRemote.Success -> {
                        result.data?.documents?.forEach { document ->
                            val usuario = document.toObject<Usuario>()
                            if (usuario?.uid == Firebase.auth.uid)
                                usuarioCargado.postValue(usuario)
                        }
                    }
                    is ResourceRemote.Error -> {
                        var msg = result.message
                        when (result.message) {
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

    fun cerrarSesion() {
        Firebase.auth.signOut()
        sesionCerrada.value = true
    }
}