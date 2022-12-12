package com.edwinacubillos.librosapp.ui.mislibros

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.edwinacubillos.librosapp.R
import com.edwinacubillos.librosapp.databinding.FragmentMisLibrosBinding
import com.edwinacubillos.librosapp.local.Libro

class MisLibrosFragment : Fragment() {

    private lateinit var misLibrosBinding: FragmentMisLibrosBinding
    private lateinit var misLibrosViewModel: MisLibrosViewModel
    private val args: MisLibrosFragmentArgs by navArgs()
    private lateinit var librosAdapter: LibrosAdapter
    private var librosList: ArrayList<Libro> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        misLibrosBinding = FragmentMisLibrosBinding.inflate(inflater, container, false)
        val root: View = misLibrosBinding.root

        misLibrosViewModel = ViewModelProvider(this)[MisLibrosViewModel::class.java]

        misLibrosViewModel.cargarLibros()

        val cargaLibrosObserver = Observer<ArrayList<Libro>> { listaLibros ->
            librosList = listaLibros
            librosAdapter.appendItems(librosList)
        }

        misLibrosViewModel.cargaLibros.observe(viewLifecycleOwner, cargaLibrosObserver)

        if (args.libro != null) {
            val libro: Libro = args.libro!!
            println(libro.nombre)
        }

        librosAdapter = LibrosAdapter(librosList = librosList,
            onItemLongClicked = { onItemLongClicked(it) })

        misLibrosBinding.librosRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MisLibrosFragment.requireContext())
            adapter = librosAdapter
            setHasFixedSize(false)
        }

        misLibrosBinding.nuevoLibroButton.setOnClickListener {
            findNavController().navigate(MisLibrosFragmentDirections.actionNavigationMisLibrosToNuevoLibroFragment())
        }

        return root
    }

    private fun onItemLongClicked(libro: Libro) {
        val alertDialog: AlertDialog? = activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.apply {
                setMessage("Desea eliminar el libro ${libro.nombre}?")
                setPositiveButton(R.string.aceptar) { dialog, id ->
                    misLibrosViewModel.borrarLibro(libro)
                    misLibrosViewModel.cargarLibros()
                }
                setNegativeButton(R.string.cancelar) { dialog, id ->

                }
            }
            builder.create()
        }
        alertDialog?.show()
    }
}