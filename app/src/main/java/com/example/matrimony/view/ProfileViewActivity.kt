package com.example.matrimony.view

import android.R
import android.os.Bundle
import android.provider.ContactsContract
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.matrimony.MyClickListener
import com.example.matrimony.adapter.GalleryViewPagerAdapter
import com.example.matrimony.databinding.ActivityProfileViewBinding
import com.example.matrimony.model.Profile


class ProfileViewActivity : AppCompatActivity(), MyClickListener {
    lateinit var binding :ActivityProfileViewBinding
    private var profileList = arrayListOf<Profile>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileViewBinding.inflate(layoutInflater)
        supportActionBar?.hide()
        setContentView(binding.root)

        val profile = intent?.extras?.get("profile") as Profile
        profileList = intent?.extras?.get("profileList") as ArrayList<Profile>

        binding.gallery.offscreenPageLimit = 3
        binding.gallery.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        binding.gallery.setPageTransformer { page, position ->
            val myOffset: Float = position * -(2 * 150)
            if (binding.gallery.orientation == ViewPager2.ORIENTATION_HORIZONTAL) {
                if (ViewCompat.getLayoutDirection(binding.gallery) == ViewCompat.LAYOUT_DIRECTION_RTL) {
                    page.translationX = -myOffset
                } else {
                    page.translationX = myOffset
                }
            }
        }
        binding.model = profile
        binding.myClickListener = this
        binding.executePendingBindings()

    }

    override fun listView() {

    }

    override fun showGalleryView() {
        binding.isGalleryViewEnabled = true
        binding.gallery.adapter = GalleryViewPagerAdapter(this, profileList, this)
        binding.executePendingBindings()
    }

    override fun closeGalleryView() {
        binding.isGalleryViewEnabled = false
        binding.executePendingBindings()
    }

    override fun goBack() {
        finish()
    }

    override fun updateMatches(arrayList: ArrayList<Profile>) {

    }
}