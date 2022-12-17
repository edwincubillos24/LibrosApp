package com.edwinacubillos.librosapp.server.repository

import com.edwinacubillos.librosapp.server.NYTimes

class LibrosServerRepository {

    private val apiKey = "d4TStilVORriHVOR8F4GZKIgINKDQ8Pz"

    suspend fun obtenerLibros() = NYTimes.retrofit.obtenerLibros(apiKey)

}