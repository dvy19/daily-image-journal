package com.example.dailyjournalpic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope

import com.example.dailyjournalpic.JournalDatabase
import com.example.dailyjournalpic.JournalEntity
import com.example.dailyjournalpic.JournalRepository
import kotlinx.coroutines.launch

class ReviewViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val dao =
        JournalDatabase
            .getDatabase(application)
            .journalDao()

    private val repository =
        JournalRepository(dao)

    val journals =
        repository.getAllJournals()

    fun saveJournal(
        imagePath: String,
        note: String,
        date: String,
        time: String
    ) {

        viewModelScope.launch {

            repository.insertJournal(

                JournalEntity(
                    imagePath = imagePath,
                    note = note,
                    date = date,
                    time = time
                )
            )
        }
    }

    fun update(journal: JournalEntity) = viewModelScope.launch { repository.update(journal) }
    fun delete(journal: JournalEntity) = viewModelScope.launch { repository.delete(journal) }
    suspend fun getById(id: Int): JournalEntity = repository.getById(id)


}