package com.edwinacubillos.librosapp.firebase.model

data class Usuario (
    var uid: String? = null,
    var nombre: String? = null,
    var correo: String? = null,
    var genero: String? = null,
    var generosFavoritos: String? = null,
    var urlFoto: String? = null
)