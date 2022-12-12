package com.edwinacubillos.librosapp.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Libro::class], version = 1, exportSchema = false)
abstract class LibroDatabase : RoomDatabase() {

    abstract fun LibrosDao() : LibrosDao

}