package com.example.rockpaperscissors

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_score.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class ScoreActivity : AppCompatActivity() {

    private val gameHistory = arrayListOf<RockPaperScissorsTable>()
    private lateinit var rpsRepo : RockPaperScissorsRepository
    private val rpsAdapter = RockPaperScissorsAdapter(gameHistory)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)

        //This line of code will enable a back button to let you go back to the main page
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        rpsRepo = RockPaperScissorsRepository(this)

        initViews()
    }

    private fun initViews() {
        rvScoreList.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rvScoreList.adapter = rpsAdapter
        rvScoreList.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        getResultsFromDatabase()
    }

    private fun deleteGameHistory() {
        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.IO) {
                rpsRepo.deleteAllGames()
            }
            getResultsFromDatabase()
        }
    }

    private fun getResultsFromDatabase() {
        CoroutineScope(Dispatchers.Main).launch {
            val results = withContext(Dispatchers.IO) {
                rpsRepo.getAllGames()
            }
            this@ScoreActivity.gameHistory.clear()
            this@ScoreActivity.gameHistory.addAll(results)
            this@ScoreActivity.rpsAdapter.notifyDataSetChanged()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_game_history, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_deleteHistory -> {
                deleteGameHistory()
                true
            }
            android.R.id.home -> {
                val intent = Intent()
                setResult(Activity.RESULT_OK, intent)
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
