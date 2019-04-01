package com.example.univerapp.entity

import java.io.Serializable

data class Test(
        val id : Int,
        val title : String?,
        val serial_number : Int?,
        val course_id : Int?
) : Serializable
