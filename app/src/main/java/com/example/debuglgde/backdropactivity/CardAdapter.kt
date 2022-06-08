package com.example.debuglgde.backdropactivity

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.debuglgde.Card
import com.example.debuglgde.R
import com.example.debuglgde.databinding.CardViewBinding

class CardAdapter(private val cardsList:MutableList<Card>) : RecyclerView.Adapter<CardAdapter.ViewHolder>() {

    private var context:Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardAdapter.ViewHolder {
        context = parent.context
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.card_view, parent, false))

    }

    override fun onBindViewHolder(holder: CardAdapter.ViewHolder, position: Int) {
        val card = cardsList[position]

        holder.binding.productName.text = card.name
        holder.binding.productBrand.text = card.brand
        holder.binding.productDescription.text = card.description
    }

    override fun getItemCount(): Int = cardsList.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val binding = CardViewBinding.bind(itemView)

        init {
            //seteo de card seleccionable
            binding.cardContainer.setOnLongClickListener {
                binding!!.cardContainer.isChecked = !binding!!.cardContainer.isChecked
                true
            }
        }
    }
}