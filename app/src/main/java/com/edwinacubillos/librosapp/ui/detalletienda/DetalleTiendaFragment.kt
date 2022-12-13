package com.edwinacubillos.librosapp.ui.detalletienda

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.edwinacubillos.librosapp.R
import com.edwinacubillos.librosapp.databinding.FragmentDetalleTiendaBinding
import com.edwinacubillos.librosapp.databinding.FragmentTiendasBinding
import com.squareup.picasso.Picasso

class DetalleTiendaFragment : Fragment() {

    private lateinit var detalleTiendaViewModel: DetalleTiendaViewModel
    private lateinit var detalleTiendabinding: FragmentDetalleTiendaBinding
    private val args: DetalleTiendaFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle? ): View? {
        detalleTiendabinding = FragmentDetalleTiendaBinding.inflate(inflater, container, false)
        detalleTiendaViewModel = ViewModelProvider(this)[DetalleTiendaViewModel::class.java]

        val tienda = args.tienda

        with(detalleTiendabinding){
            nombreTextView.text = tienda?.nombre
            direccionTextView.text = tienda?.direccion
            horarioTextView.text = tienda?.horario
            Picasso.get().load(tienda?.urlFoto).into(fotoImageView)
            resenasTextView.text = tienda?.resenas
            telefonoTextView.text = tienda?.telefono

            mapImageButton.setOnClickListener {
                findNavController().navigate(DetalleTiendaFragmentDirections.actionNavigationDetalleTiendaToNavigationMap(tienda))
            }
        }



        val view = detalleTiendabinding.root
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // TODO: Use the ViewModel
    }

}