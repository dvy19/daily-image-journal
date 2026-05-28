package com.example.dailyjournalpic

class JournalRepository(
    private val dao: JournalDao
) {

    suspend fun insertJournal(journal: JournalEntity) {
        dao.insertJournal(journal)
    }

    fun getAllJournals() = dao.getAllJournals()
}