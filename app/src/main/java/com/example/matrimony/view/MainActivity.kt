package com.example.matrimony.view

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.matrimony.adapter.ProfileViewPager
import com.example.matrimony.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    val tabMenu = arrayOf("Matches", "Just Joined", "Interested", "Rejected", "Viewed","Matches", "Just Joined", "Interested", "Rejected", "Viewed")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = ProfileViewPager(supportFragmentManager, lifecycle)
        binding.viewpager.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewpager) { tab, position ->
            tab.text = tabMenu[position]
        }.attach()

        val tabs = binding.tabLayout.getChildAt(0) as ViewGroup
        for (i in 0 until tabs.childCount) {
            val tab: View = tabs.getChildAt(i)
            val layoutParam = tab.layoutParams as LinearLayout.LayoutParams
            layoutParam.setMargins(18, 12, 9, 12)
            tab.layoutParams = layoutParam
            tab.requestLayout()
        }
    }
}