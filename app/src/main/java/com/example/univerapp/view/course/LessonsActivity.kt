package com.example.univerapp.view.course

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.example.courseapp.core.utils.SearchTextWatcher
import com.example.courseapp.core.utils.ToolbarSearchListener
import com.example.courseapp.core.utils.showKeyboard
import com.example.univerapp.R
import com.example.univerapp.adapter.Adapter
import com.example.univerapp.entity.Course
import com.example.univerapp.entity.Lesson
import kotlinx.android.synthetic.main.activity_lessons.*
import java.io.Serializable

class LessonsActivity : AppCompatActivity(), ToolbarSearchListener, Adapter.OnItemClickListener {

    private var searchTextWatcher: SearchTextWatcher?= null
    private var adapter : Adapter?= null
    private var course : Course?= null
    private var lessons = ArrayList<Lesson>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lessons)
        course = intent.extras.get("course") as Course
        initToolbar()
        initRecycler()
        loadData()
    }

    private fun loadData() {
        val items = arrayListOf<Lesson>(
                Lesson(0, "Математика 1", "bla bla bla", null, 0, 0),
                Lesson(1, "Информатика 3", "bla bla bla", null, 0, 0),
                Lesson(2, "Физика 2", "bla bla bla", null, 0, 0)
        )
        lessons.clear()
        lessons.addAll(items)
        adapter?.updateList(items)
    }


    private fun initRecycler() {
        adapter = Adapter(this, arrayListOf())
        recycler.layoutManager = LinearLayoutManager(this)
        adapter?.setOnItemClickListener(this)
        recycler.adapter = adapter
    }

    override fun onToolbarSearchTextChanged(query: String) {
        val tempList = lessons.filter {item ->
            val tempTitle = item.title?.toLowerCase()
            val tempContext = item.text?.toLowerCase()
            tempTitle?.startsWith(query.toLowerCase())!! || tempContext?.startsWith(query.toLowerCase())!!
        }
        adapter?.updateList(tempList)
    }

    private fun initToolbar() {
        toolbarTV.text = course?.title
        searchTextWatcher = SearchTextWatcher()
        searchIV.setOnClickListener {
            searchEditText.visibility = View.VISIBLE
            searchTextWatcher?.setToolbarSearchListener(this)
            searchEditText.addTextChangedListener(searchTextWatcher)
            showKeyboard(searchEditText, this)
        }
        backIV.setOnClickListener {
            onBackPressed()
        }
    }

    override fun onBackPressed() {
        if (isSearchVisible()) {
            hideSearch()
        } else {
            super.onBackPressed()
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


    override fun onItemClick(item: Any) {
        val intent = Intent(this, LessonDetailsActivity::class.java)
        intent.putExtra("lesson", item as Serializable)
        startActivity(intent)
    }
}
