package com.cal.kotlinquizappproject.domain

data class UserModel(
    val id:Int,
    val name:String,
    val picture:String,
    var score:Int
)
