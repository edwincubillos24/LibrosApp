package com.edwinacubillos.librosapp.server.model


import com.google.gson.annotations.SerializedName

data class Lists(
    @SerializedName("books")
    val books: List<Book?>? = null,
    @SerializedName("display_name")
    val displayName: String? = null,
    @SerializedName("list_id")
    val listId: Int? = null,
    @SerializedName("list_image")
    val listImage: Any? = null,
    @SerializedName("list_image_height")
    val listImageHeight: Any? = null,
    @SerializedName("list_image_width")
    val listImageWidth: Any? = null,
    @SerializedName("list_name")
    val listName: String? = null,
    @SerializedName("list_name_encoded")
    val listNameEncoded: String? = null,
    @SerializedName("updated")
    val updated: String? = null
)