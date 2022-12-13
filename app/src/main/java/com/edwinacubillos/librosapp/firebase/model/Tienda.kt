package com.edwinacubillos.librosapp.firebase.model

import java.io.Serializable

data class Tienda(
    var id: String? = null,
    var nombre: String? = null,
    var direccion: String? = null,
    var horario: String? = null,
    var urlFoto: String? = null,
    var telefono: String? = null,
    var resenas: String? = null,
    var latitud: Double? = null,
    var longitud: Double? = null
) : Serializable
