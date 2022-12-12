package com.edwinacubillos.librosapp.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.sql.Types.NULL

@Entity(tableName = "tabla_libros")
data class Libro(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id: Int = NULL,
    @ColumnInfo(name = "nombre") var nombre: String = "",
    @ColumnInfo(name = "autor") var autor: String = "",
    @ColumnInfo(name = "paginas") var paginas: Int = 0,
    @ColumnInfo(name = "puntaje") var puntaje: Double = 0.0,
    @ColumnInfo(name = "generos") var generos: String = ""
) : Serializable
