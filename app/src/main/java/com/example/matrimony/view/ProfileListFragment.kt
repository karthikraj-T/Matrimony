package com.example.matrimony.view

import android.os.Bundle
import android.os.Handler
import android.provider.ContactsContract
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.widget.NestedScrollView
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.matrimony.MyClickListener
import com.example.matrimony.R
import com.example.matrimony.adapter.ProfileListAdapter
import com.example.matrimony.databinding.FragmentProfileListBinding
import com.example.matrimony.db.ProfileHelper
import com.example.matrimony.model.Profile
import com.example.matrimony.viewmodels.ProfileListViewModel
import com.example.matrimony.viewmodels.ProfileListViewModelFactory

class ProfileListFragment : Fragment() , MyClickListener {
    lateinit var binding: FragmentProfileListBinding
    lateinit var viewModel: ProfileListViewModel
    var isListView = false
    private var visibleCount = 10
    private var lastItem = 0
    private var count= 0
    private var profileList = arrayListOf<Profile>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.profileList.layoutManager = linearLayoutManager
        val profileHelper = ProfileHelper(requireContext().applicationContext)
        val viewModelFactory = ProfileListViewModelFactory(profileHelper)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ProfileListViewModel::class.java)

        //Insert
        viewModel.insertProfiles()
        binding.profileList.adapter = ProfileListAdapter(requireContext(), profileList, isListView, this)
        setDataToAdapter()

        binding.profileList.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (count >= 50)
                    return
                count = (binding.profileList.layoutManager as LinearLayoutManager).itemCount
                lastItem = (binding.profileList.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()

                if(count <= (lastItem + visibleCount))
                {
                    binding.isProgressEnable = true
                    binding.executePendingBindings()
                    loadMore()
                }
            }
        })
        binding.model = viewModel
        binding.myClickListener = this
        binding.isProgressEnable = false
        binding.executePendingBindings()

    }

    private fun setDataToAdapter() {
        viewModel.getProfileList().observe(viewLifecycleOwner, Observer {
            for (profile in it) {
                Log.i("${profile.id}", profile.name)
            }
            profileList.addAll(it)
            binding.profileList.adapter?.notifyDataSetChanged()
            binding.matchCount = "${profileList.size} Matches"
            binding.executePendingBindings()
        })
    }

    override fun listView() {
        isListView = !isListView
        count =0
        binding.profileList.adapter = ProfileListAdapter(requireContext(), profileList, isListView, this)

        binding.isListView = isListView
        binding.executePendingBindings()

        Toast.makeText(context, "clicked", Toast.LENGTH_LONG).show()
    }

    override fun showGalleryView() {

    }

    override fun closeGalleryView() {

    }

    override fun goBack() {

    }

    override fun updateMatches(arrayList: ArrayList<Profile>) {
        binding.matchCount = "${arrayList.size} Matches"
        binding.executePendingBindings()
    }

    private fun loadMore()
    {
        val handler = Handler()
        handler.post(Runnable
        {
            setDataToAdapter()
        })

        handler.postDelayed(Runnable {
            binding.isProgressEnable = false
            binding.executePendingBindings()
        },3000)

    }

}