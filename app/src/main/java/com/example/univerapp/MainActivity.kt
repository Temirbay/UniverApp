package com.example.univerapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.univerapp.adapter.Adapter
import com.example.univerapp.entity.Course
import kotlinx.android.synthetic.main.activity_main.*
import android.R.attr.divider
import android.support.v7.widget.DividerItemDecoration
import android.R.attr.divider
import android.content.Intent
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.View
import com.example.courseapp.core.utils.SearchTextWatcher
import com.example.courseapp.core.utils.ToolbarSearchListener
import com.example.courseapp.core.utils.showKeyboard
import com.example.univerapp.view.course.LessonsActivity
import com.example.univerapp.view.test.QuestionsActivity
import com.example.univerapp.view.test.ResultsActivity
import java.io.Serializable

class MainActivity : AppCompatActivity(), Adapter.OnCourseClickListener, ToolbarSearchListener {

    private var searchTextWatcher: SearchTextWatcher?= null
    private lateinit var adapter: Adapter
    private var courses = arrayListOf<Course>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecycler()
        initToolbar()
        loadData()
        bnResults.setOnClickListener {
            val intent = Intent(this, ResultsActivity::class.java)
            startActivity(intent)
        }
    }

    private fun loadData() {
        val items = arrayListOf<Course>(
                Course(0, "Математика", "bla bla bla", null, 0),
                Course(0, "Информатика", "bla bla bla", null, 0),
                Course(0, "Физика", "bla bla bla", null, 0)

        )
        courses.clear()
        courses.addAll(items)
        adapter.updateList(items)
    }

    private fun initToolbar() {
        toolbarTV.text = "Курстар"
        searchTextWatcher = SearchTextWatcher()
        searchIV.setOnClickListener {
            searchEditText.visibility = View.VISIBLE
            searchTextWatcher?.setToolbarSearchListener(this)
            searchEditText.addTextChangedListener(searchTextWatcher)
            showKeyboard(searchEditText, this)
        }
    }

    override fun onBackPressed() {
        if (isSearchVisible()) {
            hideSearch()
        } else {

        }
    }


    private fun hideSearch() {
        searchEditText.visibility = View.GONE
        searchEditText.setText("")
        searchEditText.removeTextChangedListener(searchTextWatcher)
    }

    private fun isSearchVisible(): Boolean {
        return searchEditText.visibility == View.VISIBLE
    }


    override fun onToolbarSearchTextChanged(query: String) {
        val tempList = courses.filter {item ->
            val tempTitle = item.title?.toLowerCase()
            tempTitle?.startsWith(query.toLowerCase())!!
        }
        adapter.updateList(tempList)
    }

    private fun initRecycler() {
        adapter = Adapter(this, arrayListOf())
        adapter.setOnCourseClickListener(this)
        recycler.layoutManager = LinearLayoutManager(this) as RecyclerView.LayoutManager?
        recycler.adapter = adapter
    }

    override fun onInfoShowClick(course: Course) {
        val intent = Intent(this, LessonsActivity::class.java)
        intent.putExtra("course", course as Serializable)
        startActivity(intent)
    }

    override fun onTestShowClick(course: Course) {
        val intent = Intent(this, QuestionsActivity::class.java)
        intent.putExtra("course", course as Serializable)
        startActivity(intent)
    }

}
