package com.example.rockpaperscissors

import android.content.Context

/*The double exclamation mark (or double bang) converts a value to a non-null type, which
  means that the value never can be null and otherwise will give a nullpointerexeption*/



class RockPaperScissorsRepository (context: Context) {

    private val rpsDao: RockPaperScissorsDao

    init {
        val database = RockPaperScissorsRoomDatabase.getDatabase(context)
        rpsDao = database!!.rpsDao()
    }

    suspend fun getAllGames(): List<RockPaperScissorsTable> = rpsDao.getAllGames()

    suspend fun insertGame(rockPaperScissorsTable: RockPaperScissorsTable) = rpsDao.insertGame(rockPaperScissorsTable)

    suspend fun deleteAllGames() = rpsDao.deleteAllGames()

}