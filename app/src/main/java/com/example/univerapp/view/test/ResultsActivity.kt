package com.example.univerapp.view.test

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.TestLooperManager
import android.support.v7.widget.LinearLayoutManager
import com.example.univerapp.R
import com.example.univerapp.adapter.Adapter
import com.example.univerapp.entity.TestResult
import kotlinx.android.synthetic.main.activity_results.*

class ResultsActivity : AppCompatActivity() {

    private lateinit var adapter: Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)
        initViews()
        initRecycler()
        loadFakeData()
    }

    private fun loadFakeData() {
        val items = arrayListOf<TestResult>(
                TestResult(0, "Математика", "30", "12.10.2019"),
                TestResult(1, "Физика", "100", "13.10.2019"),
                TestResult(2, "Информатика", "10", "15.10.2019")
        )
        adapter.updateList(items)
    }


    private fun initRecycler() {
        adapter = Adapter(this, arrayListOf())
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = adapter
    }

    private fun initViews() {
        backIV.setOnClickListener {
            onBackPressed()
        }
    }


}
