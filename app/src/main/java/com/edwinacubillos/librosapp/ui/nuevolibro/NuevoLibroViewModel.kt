package com.edwinacubillos.librosapp.ui.nuevolibro

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.edwinacubillos.librosapp.local.Libro
import com.edwinacubillos.librosapp.local.repository.LibrosRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.sql.Types.NULL

class NuevoLibroViewModel : ViewModel() {

    val librosRepository = LibrosRepository()

    val errorMsg: MutableLiveData<String> by lazy{
        MutableLiveData<String>()
    }

    fun validarDatos(nombre: String, autor: String, paginas: Int, puntaje: Double, generos: String) {
        if (nombre.isEmpty() || autor.isEmpty() || paginas == 0){
            errorMsg.value = "Debe digitar el nombre, el autor y el número de páginas"
        } else {
            guardarLibro(nombre, autor, paginas, puntaje, generos)
        }
    }

    fun guardarLibro(nombre: String, autor: String, paginas: Int, puntaje: Double, generos: String) {
        val libro = Libro(NULL, nombre, autor, paginas, puntaje, generos)

        GlobalScope.launch {
            librosRepository.guardarLibro(libro)
        }
    }



}