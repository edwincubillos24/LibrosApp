package com.edwinacubillos.librosapp.firebase

import android.util.Log
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class TiendasRepository {

    private var db = Firebase.firestore

    suspend fun cargarTiendas(): ResourceRemote<QuerySnapshot?> {
        return try{
            val docRef = db.collection("tiendas")
            val result = docRef.get().await()
            ResourceRemote.Success(data = result)
        } catch (e: FirebaseFirestoreException){
            e.localizedMessage?.let { Log.e("FirestoreException", it) }
            ResourceRemote.Error(message = e.localizedMessage)
        } catch (e: FirebaseNetworkException){
            e.localizedMessage?.let { Log.e("RegisterNewtException", it) }
            ResourceRemote.Error(message = e.localizedMessage)
        }

    }


}