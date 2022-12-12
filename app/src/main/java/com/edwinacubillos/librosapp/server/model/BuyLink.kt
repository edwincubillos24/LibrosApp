package com.edwinacubillos.librosapp.server.model


import com.google.gson.annotations.SerializedName

data class BuyLink(
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("url")
    val url: String? = null
)