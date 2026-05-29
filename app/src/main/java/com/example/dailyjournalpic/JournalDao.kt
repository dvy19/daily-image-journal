package com.example.dailyjournalpic

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface JournalDao {

    @Insert
    suspend fun insertJournal(journal: JournalEntity)

    @Query("SELECT * FROM journal_table ORDER BY id DESC")
    fun getAllJournals(): Flow<List<JournalEntity>>
}

/*
==============================================================================================
this called database access layer
==============================================================================================

@Dao = data access object
    an annotation

the communication bridge between app and database
it is used to insert, get, update and delete data

==============================================================================================
UI -> ViewModel -> Repository -> Dao -> Room -> Sqlite

==============================================================================================

interface, to define the operations
room automatically implements them by generating SQl code

==============================================================================================

@Insert an annotation to insert the data

==============================================================================================

suspend means function run in background
    database operations run inside the coroutines

==============================================================================================

JournalEntity is passed as an argument which room will insert in the table

==============================================================================================

@Query is used to get the data from database
    needs Sql to be written in room

it selects all columns from journal_table and order them on basis of id as descending order
    sort newest first

it returns the list of object named as JournalEntity

==============================================================================================

Flow

a coroutine based stream that helps in getting multiple values.
suspend gets only 1 at a time

Flow<T> gets values of type T

Producer => like repo fetching data
intermediaries => transform or filter values, map etc.
consumer => collect the values like UI

Cold stream means it can run only when its collected.
Sequential nature: Flows emit values one after another, not in parallel.

stream of journal entries that updates automatically whenever the database changes.

When your ViewModel or UI calls .collect { journals -> ... }, Room starts observing the journal_table.

If you stop collecting (e.g., UI destroyed), Room stops sending updates.

no Manual refresh needed


 */