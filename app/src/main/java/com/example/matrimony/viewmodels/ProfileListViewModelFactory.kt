package com.example.matrimony.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.matrimony.db.ProfileHelper

class ProfileListViewModelFactory(var profileHelper: ProfileHelper) : ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(ProfileHelper::class.java).newInstance(profileHelper)
    }
}