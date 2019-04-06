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
                Lesson(0, "Математика 1", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", null, 0, 0),
                Lesson(1, "Информатика 3", "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).", null, 0, 0),
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
