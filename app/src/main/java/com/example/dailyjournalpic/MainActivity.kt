package com.example.dailyjournalpic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.room.Room
import com.example.dailyjournalpic.ui.theme.DailyJournalPicTheme
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        // Create Room Database
        val database = Room.databaseBuilder(
            applicationContext,
            JournalDatabase::class.java,
            "journal_db"
        ).build()

        // Create Repository
        val repository = JournalRepository(
            database.journalDao()
        )

        setContent {

            DailyJournalPicTheme {

                AppNavigation(

                )
            }
        }
    }
}
