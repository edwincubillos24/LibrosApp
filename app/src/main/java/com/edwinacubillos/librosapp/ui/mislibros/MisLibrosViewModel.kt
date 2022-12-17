package com.edwinacubillos.librosapp.ui.mislibros

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.edwinacubillos.librosapp.local.Libro
import com.edwinacubillos.librosapp.local.repository.LibrosRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MisLibrosViewModel : ViewModel() {

    val misLibrosRepository = LibrosRepository()

    val cargaLibros: MutableLiveData<ArrayList<Libro>> by lazy{
        MutableLiveData<ArrayList<Libro>>()
    }

    fun cargarLibros() {
        GlobalScope.launch {
            cargaLibros.postValue(misLibrosRepository.cargarLibros())
        }
    }

    fun borrarLibro(libro: Libro) {
        GlobalScope.launch {
            misLibrosRepository.borrarLibro(libro)
        }
    }
}