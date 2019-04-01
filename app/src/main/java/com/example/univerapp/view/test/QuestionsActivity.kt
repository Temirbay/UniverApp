package com.example.univerapp.view.test

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.example.univerapp.R
import com.example.univerapp.adapter.Adapter
import com.example.univerapp.entity.Answer
import com.example.univerapp.entity.Course
import com.example.univerapp.entity.Question
import kotlinx.android.synthetic.main.activity_questions.*

class QuestionsActivity : AppCompatActivity(), Adapter.OnItemClickListener {

    private var adapter : Adapter?= null
    private var course : Course?= null
    private var questions = arrayListOf<Question>()
    private var currentQuestionNumber = 0
    private var correct = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questions)
        course = intent.extras.get("course") as Course
        initToolbar()
        initRecycler()
        loadQuestions()
        initViews()
    }

    private fun loadQuestions() {
        val items = arrayListOf<Question>(
                Question(0, "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. ",
                        0),
                Question(1, "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English.",
                        0),
                Question(2, "Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. ",
                        0)
        )
        questions.clear()
        questions.addAll(items)
        startTest()
    }

    private fun loadAnswers(id: Int) {
        val items = arrayListOf<Answer>(
                Answer(0, "Lorem Ipsum has been the industry's standard", 1, 0),
                Answer(1, "It has survived not only five centuries", 0, 0),
                Answer(2, "It was popularised in the 1960s with the release of Letraset", 0, 0),
                Answer(3, "There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration", 0, 0)
        )
        adapter?.updateList(items)
    }

    private fun initRecycler() {
        adapter = Adapter(this, arrayListOf())
        adapter?.setOnItemClickListener(this)
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = adapter
    }

    private fun initToolbar() {
        toolbarTV.text = course?.title
        backIV.setOnClickListener {
            onBackPressed()
        }
    }

    private fun initViews() {
        button.setOnClickListener {
            if (button.text == "Басынан бастау") {
                startTest()
            }
            else if (button.text == "Келесі сұрақ"){
                nextQuestion()
            }
        }
        exit.setOnClickListener {
            if (resultForm.visibility == View.GONE) {
                finishTest()
            } else {
                onBackPressed()
            }
        }
    }


    private fun startTest() {
        questionForm.visibility = View.VISIBLE
        resultForm.visibility = View.GONE
        currentQuestionNumber = 0
        correct = 0
        showQuestion(currentQuestionNumber)
    }

    private fun showQuestion(id: Int) {
        button.text = "Басынан бастау"
        questionNumber.text = "$id сұрақ"
        question.text = questions[id].text
        loadAnswers(questions[id].id)
    }


    private fun nextQuestion() {
        if (currentQuestionNumber < questions.size-1) {
            currentQuestionNumber+=1
            showQuestion(currentQuestionNumber)
        }
        else {
            finishTest()
        }
    }

    private fun finishTest() {
        questionForm.visibility = View.GONE
        resultForm.visibility = View.VISIBLE
        val percent = correct.toFloat() / questions.size.toFloat() * 100F
        if (percent > 75F)
            button.visibility = View.GONE
        showProgress (percent)
        button.text = "Басынан бастау"
        tvResult.text = "Дұрыс жауап саны: $correct"
        tvTotal.text = "Барлық сұрақ саны: ${questions.size}"
    }

    private fun showProgress(percent : Float) {
        progress.setStartProgress(0f)
        progress.setEndProgress(percent)
        progress.setCircleBroken(true)
        progress.setTrackWidth(20)
        progress.setProgressDuration(3000)
        progress.setTrackEnabled(true)
        progress.setFillEnabled(false)
        progress.startProgressAnimation()
    }


    override fun onItemClick(item: Any) {
        val answer = item as Answer
        if (answer.isCorrect == 1) {
            button.text = "Келесі сұрақ"
            correct += 1
            recycler.isClickable = false
        }
    }
}
