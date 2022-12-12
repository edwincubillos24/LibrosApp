package com.edwinacubillos.librosapp.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface LibrosDao {

    @Insert
    fun guardarLibro(libro: Libro)

    @Query("SELECT * FROM tabla_libros")
    fun cargarLibros(): MutableList<Libro>

    @Delete
    fun borrarLibro(libro: Libro)

}