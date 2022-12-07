package com.edwinacubillos.librosapp.ui.libreria

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.edwinacubillos.librosapp.databinding.FragmentLibreriaBinding

class LibreriaFragment : Fragment() {

    private var libreriaBinding: FragmentLibreriaBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = libreriaBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val libreriaViewModel =
            ViewModelProvider(this).get(LibreriaViewModel::class.java)

        libreriaBinding = FragmentLibreriaBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textNotifications
        libreriaViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        libreriaBinding = null
    }
}