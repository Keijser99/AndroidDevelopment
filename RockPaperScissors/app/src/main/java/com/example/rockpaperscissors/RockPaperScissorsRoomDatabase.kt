package com.example.rockpaperscissors

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [RockPaperScissorsTable::class], version = 1, exportSchema = false)
abstract class RockPaperScissorsRoomDatabase : RoomDatabase() {

    abstract fun RockPaperScissorsDao(): RockPaperScissorsDao

    companion object {
        private const val DATABASE_NAME = "SHOPPING_LIST_DATABASE"

        @Volatile
        private var shoppingListRoomDatabaseInstance: RockPaperScissorsRoomDatabase? = null

        fun getDatabase(context: Context): RockPaperScissorsRoomDatabase? {
            if (shoppingListRoomDatabaseInstance == null) {
                synchronized(RockPaperScissorsRoomDatabase::class.java) {
                    if (shoppingListRoomDatabaseInstance == null) {
                        shoppingListRoomDatabaseInstance =
                            Room.databaseBuilder(
                                context.applicationContext,
                                RockPaperScissorsRoomDatabase::class.java,
                                DATABASE_NAME
                            ).build()
                    }
                }
            }
            return shoppingListRoomDatabaseInstance
        }
    }
}