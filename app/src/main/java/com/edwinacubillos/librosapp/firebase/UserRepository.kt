package com.edwinacubillos.librosapp.firebase

import android.util.Log
import com.edwinacubillos.librosapp.firebase.model.Usuario
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageException
import kotlinx.coroutines.tasks.await

class UserRepository {

    private var auth: FirebaseAuth = Firebase.auth

    private var db = Firebase.firestore

    suspend fun registrarUsuario(correo: String, password: String): ResourceRemote<String?> {

        return try {
            val result = auth.createUserWithEmailAndPassword(correo, password).await()
            ResourceRemote.Success(data = result.user?.uid)
        } catch (e: FirebaseAuthException) {
            e.localizedMessage?.let { Log.e("RegisterFireExcepction", it) }
            ResourceRemote.Error(message = e.localizedMessage)
        } catch (e: FirebaseNetworkException) {
            e.localizedMessage?.let { Log.e("RegisterNewtException", it) }
            ResourceRemote.Error(message = e.localizedMessage)
        }
    }

    suspend fun iniciarSesion(correo: String, password: String): ResourceRemote<String?> {
        return try {
            val result = auth.signInWithEmailAndPassword(correo, password).await()
            ResourceRemote.Success(data = result.user?.uid)
        } catch (e: FirebaseAuthException) {
            e.localizedMessage?.let { Log.e("RegisterFireException", it) }
            ResourceRemote.Error(message = e.localizedMessage)
        } catch (e: FirebaseNetworkException) {
            e.localizedMessage?.let { Log.e("RegisterNewtException", it) }
            ResourceRemote.Error(message = e.localizedMessage)
        }
    }

    suspend fun crearUsuario(usuario: Usuario): ResourceRemote<String?> {
        return try {
            usuario.uid?.let { uid ->
                db.collection("usuarios").document(uid).set(usuario).await()
            }
            ResourceRemote.Success(data = usuario.uid)
        } catch (e: FirebaseFirestoreException) {
            e.localizedMessage?.let { Log.e("FirestoreException", it) }
            ResourceRemote.Error(message = e.localizedMessage)
        } catch (e: FirebaseNetworkException) {
            e.localizedMessage?.let { Log.e("RegisterNewtException", it) }
            ResourceRemote.Error(message = e.localizedMessage)
        }
    }

    suspend fun cargarUsuario(): ResourceRemote<QuerySnapshot?> {
        return try {
            val docRef = db.collection("usuarios")
            val result = docRef.get().await()
            ResourceRemote.Success(data = result)
        } catch (e: FirebaseFirestoreException) {
            e.localizedMessage?.let { Log.e("FirestoreException", it) }
            ResourceRemote.Error(message = e.localizedMessage)
        } catch (e: FirebaseNetworkException) {
            e.localizedMessage?.let { Log.e("RegisterNewtException", it) }
            ResourceRemote.Error(message = e.localizedMessage)
        }
    }
}