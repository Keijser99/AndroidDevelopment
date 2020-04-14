package com.example.rockpaperscissors

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class MainActivity : AppCompatActivity() {

    private val gameHistory = arrayListOf<RockPaperScissorsTable>()
    private lateinit var rpsRepo : RockPaperScissorsRepository

    private var computersChoice : Int = 0
    private var yourChoice : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Rock Paper Scissors Game"

        rpsRepo = RockPaperScissorsRepository(this)

        initViews()
    }

    //This initialises what the buttons do if you click on them
    private fun initViews() {
        btnRock.setOnClickListener{
            chooseRock()
        }
        btnPaper.setOnClickListener{
            choosePaper()
        }
        btnScissors.setOnClickListener {
            chooseScissors()
        }
    }

    //This three functions initiate the things that will happen when you click on a button
    private fun chooseRock() {
        ivYou.setImageResource(R.drawable.rock)
        yourChoice = 1
        computerTurn()
        showResults(yourChoice,computersChoice)
    }

    private fun choosePaper() {
        ivYou.setImageResource(R.drawable.paper)
        yourChoice = 2
        computerTurn()
        showResults(yourChoice,computersChoice)
    }

    private fun chooseScissors() {
        ivYou.setImageResource(R.drawable.scissors)
        yourChoice = 3
        computerTurn()
        showResults(yourChoice,computersChoice)
    }

    //This function sets the random choices for the computer
    private fun computerTurn() {
        computersChoice = (1..3).random()
        when(computersChoice){
            1 -> ivComp.setImageResource(R.drawable.rock)
            2 -> ivComp.setImageResource(R.drawable.paper)
            3 -> ivComp.setImageResource(R.drawable.scissors)
        }
    }

    //This function calculates the result of 1 round of the game
    private fun showResults(yourMove : Int, compMove : Int) {

        //Same move
        if (yourMove == compMove) tvResultSection.text = "Draw!"

        //Rock & Paper
        else if (yourMove == 1 && compMove == 2) tvResultSection.text = "You lose!"
        //Rock & Scissors
        else if (yourMove == 1 && compMove == 3) tvResultSection.text = "You win!"
        //Paper & Rock
        else if (yourMove == 2 && compMove == 1) tvResultSection.text = "You win!"
        //Paper & Scissors
        else if (yourMove == 2 && compMove == 3) tvResultSection.text = "You lose!"
        //Scissors & Rock
        else if (yourMove == 3 && compMove == 1) tvResultSection.text = "You lose!"
        //Scissors & Paper
        else if (yourMove == 3 && compMove == 2) tvResultSection.text = "You win!"

        addResultToDatabase()
    }

    //This function adds the result to the database
    private fun addResultToDatabase() {
        CoroutineScope(Dispatchers.Main).launch {
            val rpsTable = RockPaperScissorsTable(
            winner = tvResultSection.text.toString(),
            computersChoice = computersChoice,
            yourChoice= yourChoice,
            date = Date().toString()
            )
            withContext(Dispatchers.IO) {
                rpsRepo.insertGame(rpsTable)
            }
        }
    }

    private fun goToGameHistory() {
        val intent = Intent(this, ScoreActivity::class.java)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_goToGameHistory -> {
                goToGameHistory()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
