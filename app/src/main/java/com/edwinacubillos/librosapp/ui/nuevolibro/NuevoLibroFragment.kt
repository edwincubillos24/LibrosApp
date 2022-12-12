package com.edwinacubillos.librosapp.ui.nuevolibro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.edwinacubillos.librosapp.databinding.FragmentNuevoLibroBinding

class NuevoLibroFragment : Fragment() {

    private lateinit var nuevoLibroViewModel: NuevoLibroViewModel
    private lateinit var nuevoLibroBinding: FragmentNuevoLibroBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        nuevoLibroBinding = FragmentNuevoLibroBinding.inflate(inflater, container, false)
        val root: View = nuevoLibroBinding.root

        nuevoLibroViewModel = ViewModelProvider(this)[NuevoLibroViewModel::class.java]

        val errorMsgObserver = Observer<String> { errorMsg ->
            Toast.makeText(requireContext(), errorMsg, Toast.LENGTH_SHORT).show()
        }

        nuevoLibroViewModel.errorMsg.observe(viewLifecycleOwner, errorMsgObserver)

        with(nuevoLibroBinding) {
            guardarButton.setOnClickListener {
                val nombre = nombreEditText.text.toString()
                val autor = autorEditText.text.toString()
                val paginas = if (paginasEditText.text.toString().isEmpty()) 0 else paginasEditText.text.toString().toInt()
                val puntaje = puntajeRatingBar.rating.toDouble()
                var generos = ""
                if (accionCheckBox.isChecked) generos = "Acción"
                if (aventuraCheckBox.isChecked) generos = generos + " Aventura"
                if (fantasiaCheckBox.isChecked) generos = generos + " Fantasia"
                if (romanceCheckBox.isChecked) generos = generos + " Romance"
                if (suspensoCheckBox.isChecked) generos = generos + " Suspenso"
                if (terrorCheckBox.isChecked) generos = generos + " Terror"

                nuevoLibroViewModel.validarDatos(nombre, autor, paginas, puntaje, generos)

                //    findNavController().navigate(NuevoLibroFragmentDirections.actionNavigationNuevoLibroToNavigationMisLibros(libro))
            }
        }


        return root
    }

}