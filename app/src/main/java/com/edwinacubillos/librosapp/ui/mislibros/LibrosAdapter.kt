package com.edwinacubillos.librosapp.ui.mislibros

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.edwinacubillos.librosapp.R
import com.edwinacubillos.librosapp.databinding.CardViewItemLibroBinding
import com.edwinacubillos.librosapp.local.Libro

class LibrosAdapter(
    private val librosList: ArrayList<Libro>,
    private val onItemLongClicked: (Libro) -> Unit
) : RecyclerView.Adapter<LibrosAdapter.LibrosViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LibrosViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_view_item_libro, parent, false)
        return LibrosViewHolder(view)
    }

    override fun onBindViewHolder(holder: LibrosViewHolder, position: Int) {
        val libro = librosList[position]
        holder.bind(libro)
        holder.itemView.setOnLongClickListener { onItemLongClicked(librosList[position])
            true }
    }

    override fun getItemCount(): Int = librosList.size

    fun appendItems(newList: ArrayList<Libro>){
        librosList.clear()
        librosList.addAll(newList)
        notifyDataSetChanged()
    }

    class LibrosViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        private val binding = CardViewItemLibroBinding.bind(itemView)

        fun bind(libro: Libro){
            binding.nombreTextView.text = libro.nombre
            binding.rankingTextView.text = libro.puntaje.toString()
        }
    }
}