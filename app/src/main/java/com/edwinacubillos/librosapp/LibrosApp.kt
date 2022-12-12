package com.edwinacubillos.librosapp

import android.app.Application
import androidx.room.Room
import com.edwinacubillos.librosapp.local.LibroDatabase

class LibrosApp : Application() {

    companion object{
        lateinit var database : LibroDatabase
    }

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(
            this,
            LibroDatabase::class.java,
            "libros_db"
        ).build()
    }
}