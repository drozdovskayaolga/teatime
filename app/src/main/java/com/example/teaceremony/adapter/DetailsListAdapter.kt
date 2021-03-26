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
import com.example.teaceremony.entity.DetailsEntity
import com.example.teaceremony.entity.TypesEntity

class DetailsListAdapter(private val onItemClicked: (DetailsEntity) -> Unit) :
    ListAdapter<DetailsEntity, DetailsListAdapter.DetailsListViewHolder>(WordsComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsListViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_in_details_list, parent, false)
        return DetailsListViewHolder(view)
    }

    override fun onBindViewHolder(holder: DetailsListViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }

    inner class DetailsListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val detailItemView: TextView = itemView.findViewById(R.id.tv_details_list)

        fun bind(detailsEntity: DetailsEntity) {

            detailItemView.setOnClickListener {
                onItemClicked(detailsEntity)
            }

            detailItemView.text = detailsEntity.name
        }
    }

    class WordsComparator : DiffUtil.ItemCallback<DetailsEntity>() {
        override fun areItemsTheSame(oldItem: DetailsEntity, newItem: DetailsEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: DetailsEntity, newItem: DetailsEntity): Boolean {
            return true
        }
    }
}