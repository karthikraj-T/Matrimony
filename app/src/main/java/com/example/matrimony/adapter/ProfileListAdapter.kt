package com.example.matrimony.adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.matrimony.MyClickListener
import com.example.matrimony.R
import com.example.matrimony.databinding.RowItemProfileListBinding
import com.example.matrimony.databinding.RowItemProfileListViewBinding
import com.example.matrimony.model.Profile
import com.example.matrimony.view.MainActivity
import com.example.matrimony.view.ProfileViewActivity
import kotlin.collections.ArrayList

class ProfileListAdapter(val context: Context, val profileList: ArrayList<Profile>, val isListView: Boolean, val myClickListener: MyClickListener) : RecyclerView.Adapter<ProfileListAdapter.MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = if (isListView) RowItemProfileListBinding.inflate(LayoutInflater.from(context),parent, false) else RowItemProfileListViewBinding.inflate(LayoutInflater.from(context),parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val profile = profileList[position]
        holder.bind(profile)
    }

    override fun getItemCount(): Int = profileList.size

    inner class MyViewHolder(val view: ViewBinding) : RecyclerView.ViewHolder(view.root) {
        fun bind(obj: Profile) {
            if (isListView) {
                (view as RowItemProfileListBinding).position = adapterPosition
                (view as RowItemProfileListBinding).model = obj
                (view as RowItemProfileListBinding).myClickListener = this@ProfileListAdapter
                (view as RowItemProfileListBinding).executePendingBindings()
            } else {
                (view as RowItemProfileListViewBinding).position = adapterPosition
                (view as RowItemProfileListViewBinding).model = obj
                (view as RowItemProfileListViewBinding).myClickListener = this@ProfileListAdapter
                (view as RowItemProfileListViewBinding).executePendingBindings()
            }
        }
    }

    private fun removeAt(position: Int) {
        profileList.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, profileList.size)
        myClickListener.updateMatches(profileList)
    }

    fun onInterest(position: Int) {
        Toast.makeText(context, context.getString(R.string.msg_interest), Toast.LENGTH_LONG).show()
        removeAt(position)
    }

    fun notInterest(position: Int) {
        Toast.makeText(context, context.getString(R.string.msg_not_interest), Toast.LENGTH_LONG).show()
        removeAt(position)
    }

    fun navigateToProfileView(profile: Profile) {
        val intent = Intent(context as MainActivity, ProfileViewActivity::class.java)
        intent.putExtra("profile", profile)
        intent.putExtra("profileList", profileList)
        (context as MainActivity).startActivity(intent)
    }

}