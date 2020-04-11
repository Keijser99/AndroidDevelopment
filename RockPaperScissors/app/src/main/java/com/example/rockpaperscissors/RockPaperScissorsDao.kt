package com.example.rockpaperscissors

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

/* DAO = Data Access Object. In this interface, the interactions within the database
 are defined, i.e. queries */
@Dao
interface RockPaperScissorsDao {

    @Query("SELECT * FROM game_history_table")
    suspend fun getAllGames(): List<RockPaperScissorsTable>

    @Insert
    suspend fun insertGame(rockPaperScissorsTable: RockPaperScissorsTable)

    @Query("DELETE FROM game_history_table")
    suspend fun deleteAllGames()

}