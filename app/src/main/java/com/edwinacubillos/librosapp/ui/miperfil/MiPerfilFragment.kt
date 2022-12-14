package com.edwinacubillos.librosapp.ui.miperfil

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.Observer
import com.edwinacubillos.librosapp.R
import com.edwinacubillos.librosapp.databinding.FragmentMiPerfilBinding
import com.edwinacubillos.librosapp.firebase.model.Usuario
import com.edwinacubillos.librosapp.ui.login.LoginActivity
import com.squareup.picasso.Picasso

class MiPerfilFragment : Fragment() {

    private lateinit var miPerfilViewModel: MiPerfilViewModel
    private lateinit var miPerfilBinding: FragmentMiPerfilBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        miPerfilBinding = FragmentMiPerfilBinding.inflate(inflater, container, false)
        miPerfilViewModel = ViewModelProvider(this)[MiPerfilViewModel::class.java]
        val view = miPerfilBinding.root

        miPerfilViewModel.cargarUsuario()

        val usuarioCargadoObserver = Observer<Usuario> { usuario ->
            with(miPerfilBinding){
                nombreTextView.text = usuario.nombre
                correoTextView.text = usuario.correo
                generoTextView.text = usuario.genero
                generosFavoritosTextView.text = usuario.generosFavoritos
                Picasso.get().load(usuario.urlFoto).into(fotoImageView)
            }
        }

        miPerfilViewModel.usuarioCargado.observe(viewLifecycleOwner, usuarioCargadoObserver)

        miPerfilBinding.cerrarSesionButton.setOnClickListener {
            miPerfilViewModel.cerrarSesion()
        }

        val errorMsgObserver = Observer<String> { errorMsg ->
            Toast.makeText(requireContext(), errorMsg, Toast.LENGTH_SHORT).show()
        }

        miPerfilViewModel.errorMsg.observe(viewLifecycleOwner, errorMsgObserver)

        val sesionCerradaObserver = Observer<Boolean>{
            val intent = Intent(requireContext(), LoginActivity::class.java)
            startActivity(intent)
        }

        miPerfilViewModel.sesionCerrada.observe(viewLifecycleOwner, sesionCerradaObserver)

        return view
    }
}