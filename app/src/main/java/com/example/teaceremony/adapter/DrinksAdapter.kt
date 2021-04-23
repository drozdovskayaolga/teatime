package com.example.teaceremony.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.drozdovskaya.teaceremony.R
import com.example.teaceremony.entity.DrinksEntity

class DrinksAdapter(private val onItemClicked: (DrinksEntity) -> Unit) :
    ListAdapter<DrinksEntity, DrinksAdapter.DrinksViewHolder>(WordsComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrinksViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_in_drinks, parent, false)
        return DrinksViewHolder(view)
    }

    override fun onBindViewHolder(holder: DrinksViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }

    inner class DrinksViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val drinkItemView: TextView = itemView.findViewById(R.id.tv_drinks)

        fun bind(drinksEntity: DrinksEntity) {

            drinkItemView.setOnClickListener {
                onItemClicked(drinksEntity)
            }

            drinkItemView.text = drinksEntity.name
        }
    }

    class WordsComparator : DiffUtil.ItemCallback<DrinksEntity>() {
        override fun areItemsTheSame(oldItem: DrinksEntity, newItem: DrinksEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: DrinksEntity, newItem: DrinksEntity): Boolean {
            return true
        }
    }
}