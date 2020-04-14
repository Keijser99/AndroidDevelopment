package com.example.rockpaperscissors

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//In this database is the information of the different rounds of the game stored
@Database(entities = [RockPaperScissorsTable::class], version = 1, exportSchema = false)
abstract class RockPaperScissorsRoomDatabase : RoomDatabase() {

    //This Dao is defined here to let it be able to work with the database (via i.e. queries)
    abstract fun RockPaperScissorsDao(): RockPaperScissorsDao

    companion object {
        private const val DATABASE_NAME = "ROCK_PAPER_SCISSORS_DATABASE"

        @Volatile
        private var RockPaperScissorsRoomDatabaseInstance: RockPaperScissorsRoomDatabase? = null

        fun getDatabase(context: Context): RockPaperScissorsRoomDatabase? {
            if (RockPaperScissorsRoomDatabaseInstance == null) {
                synchronized(RockPaperScissorsRoomDatabase::class.java) {
                    if (RockPaperScissorsRoomDatabaseInstance == null) {
                        RockPaperScissorsRoomDatabaseInstance =
                            Room.databaseBuilder(
                                context.applicationContext,
                                RockPaperScissorsRoomDatabase::class.java,
                                DATABASE_NAME
                            ).build()
                    }
                }
            }
            return RockPaperScissorsRoomDatabaseInstance
        }
    }
}