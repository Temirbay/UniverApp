package com.example.univerapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.univerapp.adapter.Adapter
import com.example.univerapp.entity.Course
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), Adapter.OnCourseClickListener {

    private lateinit var adapter: Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecycler()
        initToolbar()
    }

    private fun initToolbar() {

    }

    private fun initRecycler() {
        adapter = Adapter(this, arrayListOf())
        adapter.setOnCourseClickListener(this)
        recycler.layoutManager = LinearLayoutManager(this) as RecyclerView.LayoutManager?
        recycler.adapter = adapter
    }

    override fun onInfoShowClick(course: Course) {

    }

    override fun onTestShowClick(course: Course) {

    }

}
