package com.example.matrimony.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.matrimony.model.Profile

class ProfileHelper(context: Context) : SQLiteOpenHelper(context,"matrimony",null, 2) {
    private val TABLE_NAME = "ProfileDetails"
    override fun onCreate(p0: SQLiteDatabase?) {
        val query = ("CREATE TABLE $TABLE_NAME (" +
                "    Id          INTEGER PRIMARY KEY," +
                "    Name        TEXT," +
                "    age         TEXT," +
                "    height      TEXT," +
                "    description TEXT," +
                "    location    TEXT," +
                "    imageName   TEXT" +
                ")")
        p0?.execSQL(query)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(p0)
    }

    fun addProfile(profile: Profile) {
       try {
           val value = ContentValues()
           value.put("Id", profile.id)
           value.put("Name", profile.name)
           value.put("age", profile.age)
           value.put("description", profile.description)
           value.put("height", profile.height)
           value.put("location", profile.location)
           value.put("imageName", profile.imageName)

           val db = this.writableDatabase
           db.insert(TABLE_NAME, null, value)
           db.close()
           Log.i("Matrimony :","insert data Success")
       } catch (e: Exception) {
           e.printStackTrace()
       }
    }

    fun getAllProfiles() : ArrayList<Profile> {
        val profileList = arrayListOf<Profile>()
        try {
            val db = this.writableDatabase
            val cursor = db.rawQuery("SELECT Id, Name, age, height, description, location, imageName FROM $TABLE_NAME", null)
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    profileList.add(Profile(id = cursor.getInt(0), cursor.getString(1),
                        cursor.getString(2), cursor.getString(3),
                        cursor.getString(4), cursor.getString(5), cursor.getString(6)))
                }
                cursor.close()
            }
            Log.i("Matrimony :","fetch data Success")
        } catch (e: Exception){
            e.printStackTrace()
        }
        return profileList
    }

}