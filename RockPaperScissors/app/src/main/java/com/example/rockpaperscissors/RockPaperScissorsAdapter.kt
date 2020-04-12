package com.example.rockpaperscissors

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.score.view.*

class RockPaperScissorsAdapter(private val rpsList: List<RockPaperScissorsTable>) : RecyclerView.Adapter<RockPaperScissorsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.score, parent, false))
    }

    override fun getItemCount(): Int {
        return rpsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(rpsList[position])
    }

    inner class ViewHolder(itemView : View): RecyclerView.ViewHolder(itemView) {
        fun bind(rpsList: RockPaperScissorsTable) {
            itemView.tvWinner.text = rpsList.winner
            itemView.tvDate.text = rpsList.date

            //this two when-statements will put the corresponding pictures to the played round
            when(rpsList.computersChoice){
                1 -> itemView.ivComputerTurn.setImageResource(R.drawable.rock)
                2 -> itemView.ivComputerTurn.setImageResource(R.drawable.paper)
                3 -> itemView.ivComputerTurn.setImageResource(R.drawable.scissors)
            }
            when(rpsList.yourChoice){
                1 -> itemView.ivPlayerTurn.setImageResource(R.drawable.rock)
                2 -> itemView.ivPlayerTurn.setImageResource(R.drawable.paper)
                3 -> itemView.ivPlayerTurn.setImageResource(R.drawable.scissors)
            }
        }
    }
}