package com.edwinacubillos.librosapp.ui.tiendas

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.edwinacubillos.librosapp.R
import com.edwinacubillos.librosapp.databinding.CardViewItemTiendaBinding
import com.edwinacubillos.librosapp.firebase.model.Tienda
import com.squareup.picasso.Picasso

class TiendasAdapter(
    private val tiendasList: ArrayList<Tienda>,
    private val onItemClicked: (Tienda) -> Unit
) : RecyclerView.Adapter<TiendasAdapter.TiendasViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TiendasViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_view_item_tienda, parent, false)
        return TiendasViewHolder(view)
    }

    override fun onBindViewHolder(holder: TiendasViewHolder, position: Int) {
        val tienda = tiendasList[position]
        holder.bind(tienda)
        holder.itemView.setOnClickListener { onItemClicked(tiendasList[position]) }
    }

    override fun getItemCount(): Int = tiendasList.size

    fun appendItems(newList: ArrayList<Tienda>){
        tiendasList.clear()
        tiendasList.addAll(newList)
        notifyDataSetChanged()
    }

    class TiendasViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = CardViewItemTiendaBinding.bind(itemView)

        fun bind(tienda: Tienda) {
            binding.nombreTextView.text = tienda.nombre
            binding.horarioTextView.text = tienda.horario
            Picasso.get().load(tienda.urlFoto).into(binding.fotoImageView)
        }
    }

}