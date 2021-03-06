package com.example.univerapp.entity

import java.io.Serializable

data class Teacher(
    val id : Int,
    val uuid: String,
    val username : String,
    val password : String,
    val specialization_id : Int
) : Serializable