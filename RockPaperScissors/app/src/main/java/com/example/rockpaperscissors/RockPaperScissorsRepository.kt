package com.example.rockpaperscissors

import android.content.Context

/*The double exclamation mark (or double bang) makes it so that if the value is ever going
  to be null, it will throw an exception*/

//This repository will be used to access the Dao
class RockPaperScissorsRepository (context: Context) {

    private val rpsDao: RockPaperScissorsDao

    init {
        val database = RockPaperScissorsRoomDatabase.getDatabase(context)
        rpsDao = database!!.RockPaperScissorsDao()
    }

    //This selects all the played rounds that are stored into the table
    suspend fun getAllGames(): List<RockPaperScissorsTable> = rpsDao.getAllGames()

    //This adds the latest round into the table of played rounds
    suspend fun insertGame(rockPaperScissorsTable: RockPaperScissorsTable) = rpsDao.insertGame(rockPaperScissorsTable)

    //This deletes all the round that are stored in the table
    suspend fun deleteAllGames() = rpsDao.deleteAllGames()
}