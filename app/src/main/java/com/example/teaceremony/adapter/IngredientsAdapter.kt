package com.example.teaceremony.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.drozdovskaya.teaceremony.R
import com.example.teaceremony.entity.DetailsEntity
import com.example.teaceremony.entity.IngredientsEntity
import com.example.teaceremony.entity.TypesEntity

class IngredientsAdapter(private val onItemCheck: () -> Unit) :
    ListAdapter<IngredientsEntity, IngredientsAdapter.IngredientsViewHolder>(WordsComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientsViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_in_ingredients, parent, false)
        return IngredientsViewHolder(view)
    }

    override fun onBindViewHolder(holder: IngredientsViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }

    inner class IngredientsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val ingredientsItemView: CheckBox = itemView.findViewById(R.id.checkBox)

        fun bind(ingredientsEntity: IngredientsEntity) {

            ingredientsItemView.text = ingredientsEntity.name

            ingredientsItemView.setOnCheckedChangeListener(null)
            ingredientsItemView.isChecked = ingredientsEntity.isChecked
            ingredientsItemView.setOnCheckedChangeListener { _, isChecked ->
                ingredientsEntity.isChecked = isChecked
                onItemCheck()
            }
        }
    }

    class WordsComparator : DiffUtil.ItemCallback<IngredientsEntity>() {
        override fun areItemsTheSame(
            oldItem: IngredientsEntity,
            newItem: IngredientsEntity
        ): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(
            oldItem: IngredientsEntity,
            newItem: IngredientsEntity
        ): Boolean {
            return oldItem.name == newItem.name
        }
    }
}