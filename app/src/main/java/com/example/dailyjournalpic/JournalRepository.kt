package com.example.dailyjournalpic

class JournalRepository(
    private val dao: JournalDao
) {

    suspend fun insertJournal(journal: JournalEntity) {
        dao.insertJournal(journal)
    }

    fun getAllJournals() = dao.getAllJournals()

    suspend fun update(journal: JournalEntity) = dao.updateJournal(journal)
    suspend fun delete(journal: JournalEntity) = dao.deleteJournal(journal)
    suspend fun getById(id: Int) = dao.getJournalById(id)


}