package com.example.univerapp.entity

import java.io.Serializable

data class Answer(
        val id : Int,
        val text : String?,
        val isCorrect : Int?,
        val question_id : Int?
) : Serializable