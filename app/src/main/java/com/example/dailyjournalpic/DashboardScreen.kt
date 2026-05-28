package com.example.dailyjournalpic

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@Composable
fun DashboardScreen(
    navController: NavController
) {

    val viewModel: ReviewViewModel = viewModel()
    val journals by viewModel.journals.collectAsState()

    Scaffold(

        floatingActionButton = {

            FloatingActionButton(
                onClick = {
                    navController.navigate("camera")
                }
            ) {
                Icon(Icons.Default.Add, null)
            }
        }

    ) { padding ->

        Box(
            modifier = Modifier.
            padding(padding)
        ) {

            Text("Dashboard")


        }
    }
}