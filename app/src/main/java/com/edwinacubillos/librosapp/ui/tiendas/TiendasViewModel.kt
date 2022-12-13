package com.edwinacubillos.librosapp.ui.tiendas

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edwinacubillos.librosapp.firebase.ResourceRemote
import com.edwinacubillos.librosapp.firebase.TiendasRepository
import com.edwinacubillos.librosapp.firebase.model.Tienda
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.launch

class TiendasViewModel : ViewModel() {

    val tiendasRepository = TiendasRepository()

    private var tiendasList: ArrayList<Tienda> = ArrayList()

    val errorMsg: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val listaTienda: MutableLiveData<ArrayList<Tienda>> by lazy{
        MutableLiveData<ArrayList<Tienda>>()
    }

    fun cargarTiendas() {
        tiendasList.clear()
        viewModelScope.launch{
            val result = tiendasRepository.cargarTiendas()
            result.let { resourceRemote ->
                when(resourceRemote){
                    is ResourceRemote.Success -> {
                        resourceRemote.data?.documents?.forEach { document->
                            val tienda = document.toObject<Tienda>()
                            tienda?.let { tiendasList.add(it) }
                        }
                        listaTienda.postValue(tiendasList)
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


}