package com.example.matrimony.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.matrimony.MyClickListener
import com.example.matrimony.databinding.RowItemGalleryBinding
import com.example.matrimony.model.Profile

class GalleryViewPagerAdapter(val context: Context, val imageList: ArrayList<Profile>, val myClickListener: MyClickListener) :
    RecyclerView.Adapter<GalleryViewPagerAdapter.GalleryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        val binding = RowItemGalleryBinding.inflate(LayoutInflater.from(context), parent, false)
        return GalleryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        val profile = imageList[position]
        holder.bind(profile)
    }

    override fun getItemCount(): Int = imageList.size

    inner class GalleryViewHolder(val view: RowItemGalleryBinding) : RecyclerView.ViewHolder(view.root) {
        fun bind(obj: Profile) {
            view.model = obj
            view.myClickListener = myClickListener
            view.executePendingBindings()
        }
    }
}