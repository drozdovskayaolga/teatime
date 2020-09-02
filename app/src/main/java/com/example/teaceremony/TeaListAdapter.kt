package com.example.teaceremony

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.drozdovskaya.teaceremony.R
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation


class TeaListAdapter(private val teaImages: List<TeaData>)
    : RecyclerView.Adapter<TeaListAdapter.MyViewHolder>() {

     var onItemClickListener:((item:TeaData)->Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeaListAdapter.MyViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val photoView = inflater.inflate(R.layout.item_in_tealist, parent, false)
        return MyViewHolder(photoView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(teaImages[position])
    }

    override fun getItemCount(): Int {
        return teaImages.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        private val imageView: ImageView = itemView.findViewById(R.id.iv_tea)
        private val imageText: TextView = itemView.findViewById(R.id.tv_Tea)

        fun bind(data: TeaData){

            imageText.text = data.name

            Picasso.with(itemView.context)
                .load(data.image)
                .resize(450, 450)
                .centerCrop()
                .transform(CropCircleTransformation())
                .into(imageView)

            itemView.setOnClickListener{
                onItemClickListener?.invoke(data)
            }
        }

    }
}

