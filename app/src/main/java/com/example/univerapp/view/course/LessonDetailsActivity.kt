package com.example.univerapp.view.course

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.univerapp.R
import com.example.univerapp.entity.Lesson
import kotlinx.android.synthetic.main.activity_lesson_details.*

class LessonDetailsActivity : AppCompatActivity() {

    private var lesson : Lesson?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson_details)
        lesson = intent.extras.get("lesson") as Lesson
        initToolbar()
        lessonText.text = lesson?.text
    }


    private fun initToolbar() {
        toolbarTV.text = lesson?.title
        backIV.setOnClickListener {
            onBackPressed()
        }
    }
}
