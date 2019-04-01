package com.example.univerapp.entity

import java.io.Serializable

data class Question(
    val id : Int,
    val text : String?,
    val course_id : Int?
) : Serializable