package com.example.dailyjournalpic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ReviewViewModel(
    private val repository: JournalRepository
) : ViewModel() {

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
}