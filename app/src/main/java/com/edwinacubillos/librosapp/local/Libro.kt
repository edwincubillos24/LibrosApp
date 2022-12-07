package com.edwinacubillos.librosapp.local

import java.io.Serializable

data class Libro(
    var nombre: String = "",
    var autor: String = "",
    var paginas: Int = 0,
    var puntaje: Double = 0.0,
    var generos: String = ""
) : Serializable
