package com.example.univerapp.entity

import java.io.Serializable

data class TestResult(
        val id : Int,
        val course : String?,
        val result : String?,
        val date : String?
) : Serializable