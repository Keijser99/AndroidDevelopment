package com.example.rockpaperscissors

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="game_history_table")
data class RockPaperScissorsTable(

    @PrimaryKey(autoGenerate=true)
    @ColumnInfo(name = "id")
    var id: Long? = null,

    @ColumnInfo(name = "winner")
    var winner: String,

    @ColumnInfo(name = "computers_choice")
    var computersChoice: Int,

    @ColumnInfo(name = "your_choice")
    var yourChoice: Int,

    @ColumnInfo(name = "date")
    var date: String
)