package com.example.univerapp.entity

import java.io.Serializable

data class Lesson(
    val id : Int,
    val title : String?,
    val text : String?,
    val video : String?,
    val serial_number : Int?,
    val course_id : Int?
) : Serializable
