package com.edwinacubillos.librosapp.ui.libreria

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.edwinacubillos.librosapp.R
import com.edwinacubillos.librosapp.databinding.CardViewItemLibreriaBinding
import com.edwinacubillos.librosapp.server.model.Book
import com.squareup.picasso.Picasso

class LibreriaAdapter(
    private val librosList: ArrayList<Book>
) : RecyclerView.Adapter<LibreriaAdapter.BookViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.card_view_item_libreria, parent, false)
        return BookViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book: Book = librosList[position]
        holder.bind(book)
    }

    override fun getItemCount(): Int = librosList.size

    fun appendItems(newList: ArrayList<Book>) {
        librosList.clear()
        librosList.addAll(newList)
        notifyDataSetChanged()
    }

    class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = CardViewItemLibreriaBinding.bind(itemView)

        fun bind(book: Book) {
            with(binding) {
                nombreTextView.text = book.title
                autorTextView.text = book.author
                rankingTextView.text = book.rank.toString()
                Picasso.get().load(book.bookImage).into(imagenImageView)
            }
        }
    }
}