package com.example.univerapp.entity

import java.io.Serializable

data class Course(
        val id : Int,
        val title : String?,
        val description : String?,
        val image : String?,
        val specialization_id : Int
) : Serializable