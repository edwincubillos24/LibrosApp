package com.edwinacubillos.librosapp.ui.nuevolibro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.edwinacubillos.librosapp.databinding.FragmentNuevoLibroBinding
import com.edwinacubillos.librosapp.local.Libro

class NuevoLibroFragment : Fragment() {

    private lateinit var nuevoLibroViewModel: NuevoLibroViewModel
    private lateinit var nuevoLibroBinding: FragmentNuevoLibroBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        nuevoLibroBinding = FragmentNuevoLibroBinding.inflate(inflater, container, false)
        val root: View = nuevoLibroBinding.root

        val nuevoLibroViewModel = ViewModelProvider(this)[NuevoLibroViewModel::class.java]

        with(nuevoLibroBinding){
            guardarButton.setOnClickListener {
                val nombre = nombreEditText.text.toString()
                val autor = autorEditText.text.toString()
                val paginas = paginasEditText.text.toString().toInt()
                val puntaje = puntajeRatingBar.rating.toDouble()
                var generos = ""
                if (accionCheckBox.isChecked) generos = "Acción"
                if (aventuraCheckBox.isChecked) generos = generos + " Aventura"
                if (fantasiaCheckBox.isChecked) generos = generos + " Fantasia"
                if (romanceCheckBox.isChecked) generos = generos + " Romance"
                if (suspensoCheckBox.isChecked) generos = generos + " Suspenso"
                if (terrorCheckBox.isChecked) generos = generos + " Terror"

                val libro = Libro(nombre, autor, paginas, puntaje, generos)

                findNavController().navigate(NuevoLibroFragmentDirections.actionNavigationNuevoLibroToNavigationMisLibros(libro))
            }
        }


        return root
    }

}