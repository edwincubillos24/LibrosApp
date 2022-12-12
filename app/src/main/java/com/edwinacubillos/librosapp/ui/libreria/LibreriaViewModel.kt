package com.edwinacubillos.librosapp.ui.libreria

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edwinacubillos.librosapp.server.model.Book
import com.edwinacubillos.librosapp.server.repository.LibrosServerRepository
import kotlinx.coroutines.launch

class LibreriaViewModel : ViewModel() {

    val librosServerRepository = LibrosServerRepository()

    val librosCargados: MutableLiveData<ArrayList<Book>> by lazy {
        MutableLiveData<ArrayList<Book>>()
    }

    private var libreriaList: ArrayList<Book> = ArrayList()

    fun obtenerLibros() {
        viewModelScope.launch {
            val serverResponse = librosServerRepository.obtenerLibros()
            serverResponse.results?.lists?.forEach { lists ->
                libreriaList.addAll(lists?.books as ArrayList<Book>)
            }
            librosCargados.postValue(libreriaList)
        }
    }
}