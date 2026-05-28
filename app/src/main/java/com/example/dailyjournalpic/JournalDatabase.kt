package com.example.dailyjournalpic

import androidx.room.Database
import androidx.room.RoomDatabase
import android.content.Context
import androidx.room.Room

@Database(
    entities = [JournalEntity::class],
    version = 1
)

abstract class JournalDatabase : RoomDatabase() {

    abstract fun journalDao(): JournalDao

    companion object {

        @Volatile
        private var INSTANCE: JournalDatabase? = null

        fun getDatabase(
            context: Context
        ): JournalDatabase {

            return INSTANCE ?: synchronized(this) {

                val instance =
                    Room.databaseBuilder(
                        context.applicationContext,
                        JournalDatabase::class.java,
                        "journal_database"
                    ).build()

                INSTANCE = instance

                instance
            }
        }
    }
}