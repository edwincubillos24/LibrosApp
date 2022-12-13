package com.edwinacubillos.librosapp.ui.miperfil

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.edwinacubillos.librosapp.R
import com.edwinacubillos.librosapp.databinding.FragmentMiPerfilBinding
import com.edwinacubillos.librosapp.firebase.model.Usuario
import com.edwinacubillos.librosapp.ui.login.LoginActivity

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