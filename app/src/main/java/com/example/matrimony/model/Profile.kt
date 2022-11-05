package com.example.matrimony.model

import java.io.Serializable

data class Profile(var id: Int,
                   var name: String,
                   var age: String,
                   var height: String,
                   var description: String,
                   var location: String,
                    var imageName: String) : Serializable
