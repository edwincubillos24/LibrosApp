package com.edwinacubillos.librosapp.ui.miperfil

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.edwinacubillos.librosapp.R

class MiPerfilFragment : Fragment() {

    companion object {
        fun newInstance() = MiPerfilFragment()
    }

    private lateinit var viewModel: MiPerfilViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_mi_perfil, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MiPerfilViewModel::class.java)
        // TODO: Use the ViewModel
    }
}