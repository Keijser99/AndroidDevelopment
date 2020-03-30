package com.example.swipequiz2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
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

    /**
     * Create a touch helper to recognize when a user swipes an item from a recycler view.
     * An ItemTouchHelper enables touch behavior (like swipe and move) on each ViewHolder,
     * and uses callbacks to signal when a user is performing these actions.
     */
    private fun createItemTouchHelper(): ItemTouchHelper {

        // Callback which is used to create the ItemTouch helper. Only enables left swipe.
        // Use ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) to also enable right swipe.
        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

            // Enables or Disables the ability to move items up and down.
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                return false
            }

            // Callback triggered when a user swiped an item.
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
//                if (direction == 4){
//                    //Used to check direction of the swipe for left
//                    Toast.makeText(this@MainActivity, "Direction Left", Toast.LENGTH_SHORT).show()
//                }else if(direction == 8){
//                    //Used to check direction of the swipe for right
//                    Toast.makeText(this@MainActivity, "Direction Right", Toast.LENGTH_SHORT).show()
//                }


                if (questions.get(position).value == direction){
                    Toast.makeText(this@MainActivity,"Correct ", Toast.LENGTH_SHORT).show()
                    questions.removeAt(position)
                }else{
                    Toast.makeText(this@MainActivity,"Incorrect, The question will not be removed ", Toast.LENGTH_SHORT).show()
                }

                questionAdapter.notifyDataSetChanged()
            }
        }

        return ItemTouchHelper(callback)
    }
}