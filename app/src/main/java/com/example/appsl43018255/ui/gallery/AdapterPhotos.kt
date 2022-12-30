package com.example.appsl43018255.ui.gallery

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.appsl48399119.R
import com.example.appsl43018255.data.response.photos.PhotosResponseItem

class AdapterPhotos(private val list: List<PhotosResponseItem>) : RecyclerView.Adapter<AdapterPhotos.AdapterPhotosViewHolder>() {

    private var context : Context ?= null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterPhotosViewHolder {
        context = parent.context
        return AdapterPhotosViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_photos,parent,false))
    }

    override fun onBindViewHolder(holder: AdapterPhotosViewHolder, position: Int) {
        holder.txtAlbumId.text = list.get(position).albumId.toString()
        holder.txtId.text = list.get(position).id.toString()
        holder.txtTitle.text = list.get(position).title
        holder.txtUrl.text = list.get(position).url
        Glide.with(context!!).load(list[position].thumbnailUrl).into(holder.imgThumb);
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class AdapterPhotosViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtId : TextView = itemView.findViewById(R.id.txtId)
        var txtAlbumId : TextView = itemView.findViewById(R.id.txtAlbumId)
        var txtTitle : TextView = itemView.findViewById(R.id.txtTitle)
        var txtUrl : TextView = itemView.findViewById(R.id.txtUrl)
        var imgThumb : ImageView = itemView.findViewById(R.id.imgThumb)

    }
}