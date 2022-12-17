package com.edwinacubillos.librosapp.local.repository

import com.edwinacubillos.librosapp.LibrosApp
import com.edwinacubillos.librosapp.local.Libro
import com.edwinacubillos.librosapp.local.LibrosDao

class LibrosRepository {

    fun guardarLibro(libro: Libro) {
        val librosDao: LibrosDao = LibrosApp.database.LibrosDao()
        librosDao.guardarLibro(libro)
    }

    fun cargarLibros(): ArrayList<Libro> {
        val librosDao: LibrosDao = LibrosApp.database.LibrosDao()
        val librosList: ArrayList<Libro> = librosDao.cargarLibros() as ArrayList<Libro>
        return librosList
    }

    fun borrarLibro(libro: Libro) {
        val librosDao: LibrosDao = LibrosApp.database.LibrosDao()
        librosDao.borrarLibro(libro)
    }
}