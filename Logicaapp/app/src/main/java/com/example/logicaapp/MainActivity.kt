package com.example.logicaapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnConfirm.setOnClickListener{
            checkAnswers()
        }
    }



    /**
     * Check if the submitted answer is correct.
     */
    private fun checkAnswers() {
        val answer1 = aRow1.text.toString()
        val answer2 = aRow2.text.toString()
        val answer3 = aRow3.text.toString()
        val answer4 = aRow4.text.toString()

        if (answer1 == getString(R.string.t) && answer2 == getString(R.string.f) && answer3 == getString(R.string.f)&& answer4 == getString(R.string.f)) {
            Toast.makeText(this, getString(R.string.correct), Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, getString(R.string.incorrect), Toast.LENGTH_LONG).show()
        }
    }
}
