package com.example.matrimony.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.matrimony.db.ProfileHelper
import com.example.matrimony.model.Profile

public class ProfileListViewModel(var profileHelper: ProfileHelper) : ViewModel() {

    var totalProfileSize: String = "Matches"

    private var allProfileList: MutableLiveData<ArrayList<Profile>>?= null

    fun getProfileList(): MutableLiveData<ArrayList<Profile>> {
        if (allProfileList == null) {
            allProfileList = MutableLiveData<ArrayList<Profile>>()
            fetchAllProfiles()
        }
        totalProfileSize = "${allProfileList?.value?.size} Matches"
        return allProfileList as MutableLiveData<ArrayList<Profile>>
    }

    private fun fetchAllProfiles() {
        allProfileList?.value = profileHelper.getAllProfiles()
    }

    fun insertProfiles() {
        profileHelper.addProfile(Profile(1, "Rani", "25", "5.5", "klasfj klasjfklajsklfjskl", "chennai","one"))
        profileHelper.addProfile(Profile(2, "Lakshimi", "25", "5.5", "klasfj klasjfklajsklfjsklklasjfklajsklfjskl", "mumbai","two"))
        profileHelper.addProfile(Profile(3, "Bhargavi", "25", "5.5", "klasfj klasjfklajsklfjs klasjfklajsklfjsklkl", "US","three"))
        profileHelper.addProfile(Profile(4, "Tejeshwini", "25", "5.5", "klasfj klasjfkl  ajsklfjskl", "chennai","four"))
        profileHelper.addProfile(Profile(5, "Sanjana", "25", "5.5", "klasfj klasjfklajsklfjskl", "chennai","five"))
        profileHelper.addProfile(Profile(6, "Raji", "25", "5.5", "klasfj klasjfklaj sklfjskl", "mumbai","six"))
        profileHelper.addProfile(Profile(7, "Veni", "25", "5.5", "klasfj klasjfklaj sklfjskl  sklfjskl", "chennai","seven"))
        profileHelper.addProfile(Profile(8, "Vinaya", "25", "5.5", "klasfj klasjfkl ajsklfjskl", "America","eight"))
        profileHelper.addProfile(Profile(9, "Amutha", "25", "5.5", "klasfj klasjfklajsklfjskl", "chennai","nine"))
        profileHelper.addProfile(Profile(10, "Janani", "25", "5.5", "klasfj klasjfk  lajsklfjskl", "Asia","ten"))
    }
}