package com.example.matrimony

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("imgRes")
fun setImageResource(imageView: AppCompatImageView, imageName : String) {
    imageView.setImageResource(when(imageName) {
        "one","two","three" -> R.drawable.three
        "four","five","elven" -> R.drawable.five
        "six","seven" -> R.drawable.seven
        "eight" -> R.drawable.eight
        "nine","ten" -> R.drawable.ten
        else -> R.drawable.three
    })
}