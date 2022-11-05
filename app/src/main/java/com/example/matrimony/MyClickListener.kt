package com.example.matrimony

import com.example.matrimony.model.Profile

interface MyClickListener {
    fun listView()

    fun showGalleryView()

    fun closeGalleryView()

    fun goBack()

    fun updateMatches(arrayList: ArrayList<Profile>)
}