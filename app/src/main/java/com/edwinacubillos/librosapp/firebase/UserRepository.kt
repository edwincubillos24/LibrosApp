package com.edwinacubillos.librosapp.firebase

import android.util.Log
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class UserRepository {

    private var auth: FirebaseAuth = Firebase.auth

    suspend fun registrarUsuario(correo: String, password: String): ResourceRemote<String?> {

        return try {
            val result = auth.createUserWithEmailAndPassword(correo, password).await()
            ResourceRemote.Success(data = result.user?.uid)
        } catch (e: FirebaseAuthException){
            e.localizedMessage?.let { Log.e("RegisterFireExcepction", it) }
            ResourceRemote.Error(message = e.localizedMessage)
        } catch (e: FirebaseNetworkException){
            e.localizedMessage?.let { Log.e("RegisterNewtException", it) }
            ResourceRemote.Error(message = e.localizedMessage)
        }
    }

    suspend fun iniciarSesion(correo: String, password: String): ResourceRemote<String?> {
        return try{
            val result = auth.signInWithEmailAndPassword(correo, password).await()
            ResourceRemote.Success(data = result.user?.uid)
        } catch (e: FirebaseAuthException){
            e.localizedMessage?.let { Log.e("RegisterFireExcepction", it) }
            ResourceRemote.Error(message = e.localizedMessage)
        } catch (e: FirebaseNetworkException){
            e.localizedMessage?.let { Log.e("RegisterNewtException", it) }
            ResourceRemote.Error(message = e.localizedMessage)
        }


    }


}