package com.edwinacubillos.librosapp.server.model


import com.google.gson.annotations.SerializedName

data class ServerResponse(
    @SerializedName("copyright")
    val copyright: String? = null,
    @SerializedName("num_results")
    val numResults: Int? = null,
    @SerializedName("results")
    val results: Results? = null,
    @SerializedName("status")
    val status: String? = null
)