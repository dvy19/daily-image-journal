package com.example.dailyjournalpic

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "journal_table")
data class JournalEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val imagePath: String,

    val note: String,

    val date: String,

    val time: String
)