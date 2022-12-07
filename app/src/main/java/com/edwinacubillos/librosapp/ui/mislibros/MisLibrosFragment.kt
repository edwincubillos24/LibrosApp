package com.edwinacubillos.librosapp.ui.mislibros

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.edwinacubillos.librosapp.databinding.FragmentMisLibrosBinding
import com.edwinacubillos.librosapp.local.Libro

class MisLibrosFragment : Fragment() {

    private lateinit var misLibrosBinding: FragmentMisLibrosBinding
    private val args: MisLibrosFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View {
        misLibrosBinding = FragmentMisLibrosBinding.inflate(inflater, container, false)
        val root: View = misLibrosBinding.root

        val misLibrosViewModel = ViewModelProvider(this)[MisLibrosViewModel::class.java]

        if (args.libro != null) {
            val libro: Libro = args.libro!!
            println(libro.nombre)
        }


        misLibrosBinding.nuevoLibroButton.setOnClickListener {
            findNavController().navigate(MisLibrosFragmentDirections.actionNavigationMisLibrosToNuevoLibroFragment())
        }

        return root
    }

}