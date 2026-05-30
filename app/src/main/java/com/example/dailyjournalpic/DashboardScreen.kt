package com.example.dailyjournalpic

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import java.io.File
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment

@Composable
fun DashboardScreen(
    navController: NavController
) {

    val viewModel: ReviewViewModel = viewModel()

    val journals by viewModel
        .journals
        .collectAsState(initial = emptyList())

    Scaffold(

        floatingActionButton = {

            FloatingActionButton(
                onClick = {
                    navController.navigate("camera")
                }
            ) {

                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null
                )
            }
        }

    ) { padding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),

            contentPadding = PaddingValues(12.dp),

            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            items(journals) { journal ->

                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {

                    Column(
                        modifier = Modifier.padding(12.dp)
                    ) {

                        Image(
                            painter = rememberAsyncImagePainter(
                                File(journal.imagePath)
                            ),

                            contentDescription = null,

                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp),

                            contentScale = ContentScale.Crop
                        )

                        Spacer(
                            modifier = Modifier.height(8.dp)
                        )

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(
                                modifier = Modifier.weight(1f)
                            ) {
                                Text(
                                    text = journal.note,
                                    style = MaterialTheme.typography.bodyLarge
                                )

                                Spacer(modifier = Modifier.height(4.dp))

                                Text(
                                    text = journal.date,
                                    style = MaterialTheme.typography.bodyMedium
                                )

                                Text(
                                    text = journal.time,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }

                            Button(
                                onClick = {
                                    navController.navigate("edit/${journal.id}")
                                },
                                modifier = Modifier.height(36.dp),
                                shape = RoundedCornerShape(8.dp)
                            ) {
                                Text("Edit")
                            }
                        }

                    }
                }
            }
        }
    }
}