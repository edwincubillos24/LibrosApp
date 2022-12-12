package com.edwinacubillos.librosapp.server

import com.edwinacubillos.librosapp.server.model.ServerResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("lists/full-overview.json")
    suspend fun obtenerLibros(@Query("api-key") apiKey: String): ServerResponse

}