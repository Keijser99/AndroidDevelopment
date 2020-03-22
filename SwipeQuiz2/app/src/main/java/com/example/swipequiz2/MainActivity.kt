package com.example.swipequiz2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    // Initializing an empty ArrayList to be filled with questions
    val questions = arrayListOf<Questions>()
    val questionAdapter = QuestionsAdapter(questions)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()

        // Loads questions into the ArrayList
        addQuestions()
    }

    fun initViews() {
        // Creates a vertical Layout Manager
        rvQuestionList.layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)

        // Access the RecyclerView Adapter and load the data into it
        rvQuestionList.adapter = questionAdapter

        rvQuestionList.addItemDecoration(DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL))
    }

    // Adds animals to the empty animals ArrayList
    fun addQuestions() {
        questions.add(Questions("A 'var' and a 'val' are the same."))
        questions.add(Questions("Mobile Application Development grants 12 ETCS"))
        questions.add(Questions("In Kotlin, the 'when' operator replaces 'switch' operator in Java"))
    }
}