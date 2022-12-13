package com.edwinacubillos.librosapp.ui.tiendas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.edwinacubillos.librosapp.databinding.FragmentTiendasBinding
import com.edwinacubillos.librosapp.firebase.model.Tienda

class TiendasFragment : Fragment() {

    private lateinit var tiendasViewModel: TiendasViewModel
    private lateinit var tiendasBinding: FragmentTiendasBinding
    private lateinit var tiendasAdapter: TiendasAdapter
    private var tiendasList: ArrayList<Tienda> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        tiendasBinding = FragmentTiendasBinding.inflate(inflater, container, false)
        tiendasViewModel = ViewModelProvider(this)[TiendasViewModel::class.java]
        val view = tiendasBinding.root

        tiendasViewModel.cargarTiendas()

        tiendasAdapter = TiendasAdapter(tiendasList,
        onItemClicked = { tienda ->
            findNavController().navigate(TiendasFragmentDirections.actionNavigationTiendasToNavigationDetalleTienda(tienda))
        })

        tiendasBinding.tiendasRecyclerView.apply{
            layoutManager = LinearLayoutManager(this@TiendasFragment.requireContext())
            adapter = tiendasAdapter
            setHasFixedSize(false)
        }

        val errorMsgObserver = Observer<String> { errorMsg ->
            Toast.makeText(requireContext(), errorMsg, Toast.LENGTH_SHORT).show()
        }

        tiendasViewModel.errorMsg.observe(viewLifecycleOwner, errorMsgObserver)

        val listaTiendaObserver = Observer<ArrayList<Tienda>> { listaTienda ->
            tiendasAdapter.appendItems(listaTienda)
        }

        tiendasViewModel.listaTienda.observe(viewLifecycleOwner, listaTiendaObserver)

        return view
    }
}