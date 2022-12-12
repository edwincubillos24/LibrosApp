package com.edwinacubillos.librosapp.ui.libreria

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.edwinacubillos.librosapp.databinding.FragmentLibreriaBinding
import com.edwinacubillos.librosapp.server.model.Book

class LibreriaFragment : Fragment() {

    private lateinit var libreriaBinding: FragmentLibreriaBinding
    private lateinit var libreriaViewModel: LibreriaViewModel
    private lateinit var libreriaAdapter: LibreriaAdapter
    private var libreriaList: ArrayList<Book> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View {

        libreriaBinding = FragmentLibreriaBinding.inflate(inflater, container, false)
        val root: View = libreriaBinding.root

        libreriaViewModel = ViewModelProvider(this)[LibreriaViewModel::class.java]

        libreriaAdapter = LibreriaAdapter(libreriaList)

        libreriaBinding.libreriaRecyclerView.apply{
            layoutManager = LinearLayoutManager(this@LibreriaFragment.requireContext())
            adapter = libreriaAdapter
            setHasFixedSize(false)
        }

        libreriaViewModel.obtenerLibros()

        val librosCargadosObserver = Observer<ArrayList<Book>> { listaLibros ->
            libreriaList = listaLibros
            libreriaAdapter.appendItems(libreriaList)
        }

        libreriaViewModel.librosCargados.observe(viewLifecycleOwner, librosCargadosObserver)

        return root
    }

}