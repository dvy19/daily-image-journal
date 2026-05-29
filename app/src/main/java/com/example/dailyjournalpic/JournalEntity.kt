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


/*
==============================================================================================

Represent 1 row of the database table
table name is journal_table

==============================================================================================

Entity = a kotlin class that represents a table.

Here, each row of the table is  JournalEntity object

==============================================================================================

@Entity is called annotation, they are metadata or data about data
it tells that this class as a database table

==============================================================================================

PrimaryKey is used to identify a row uniquely.
autoGenerate = True
    SQLite automatically generate the series of primary key starting from 0

==============================================================================================

imagePath is the path of image form device storage.

==============================================================================================

we use val here, to make them immutable so that can not be changes further
 */