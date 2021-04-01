package com.example.teaceremony.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.drozdovskaya.teaceremony.R
import com.example.teaceremony.entity.TypesEntity

class TypesAdapter(private val onItemClicked: (TypesEntity) -> Unit) :
    ListAdapter<TypesEntity, TypesAdapter.TypesViewHolder>(WordsComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TypesViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_in_types, parent, false)
        return TypesViewHolder(view)
    }

    override fun onBindViewHolder(holder: TypesViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }

    inner class TypesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val typeItemView: TextView = itemView.findViewById(R.id.b_types)
        private val typeImage: ImageView = itemView.findViewById(R.id.iv_types)

        fun bind(typesEntity: TypesEntity) {

            typeItemView.setOnClickListener {
                onItemClicked(typesEntity)
            }

            typeItemView.text = typesEntity.name

            when (typesEntity.id) {
                1 -> {
                    typeImage.setImageResource(R.drawable.teamainphoto)
                }
                2 -> {
                    typeImage.setImageResource(R.drawable.coffeemainphoto)
                }
                else -> {
                    typeImage.setImageResource(R.drawable.cocktailmainphoto)
                }
            }
        }
    }

    class WordsComparator : DiffUtil.ItemCallback<TypesEntity>() {
        override fun areItemsTheSame(oldItem: TypesEntity, newItem: TypesEntity): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: TypesEntity, newItem: TypesEntity): Boolean {
            return oldItem.name == newItem.name
        }
    }
}
