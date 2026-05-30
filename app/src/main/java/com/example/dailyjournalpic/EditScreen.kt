package com.example.dailyjournalpic

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import kotlinx.coroutines.launch
import coil.compose.rememberAsyncImagePainter
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import kotlinx.coroutines.launch
val SageGreen = Color(0xFF2D5A27)        // Rich, grounded green for primary actions
val SoftSage = Color(0xFFE8F0E6)         // Light, soothing green for backgrounds
val TerracottaOrange = Color(0xFFD96B43) // Vibrant but mature orange for deletions/accents
val WarmIvory = Color(0xFFFCFBF9)        // Soft white for the app background
val Charcoal = Color(0xFF232323)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditScreen(
    journalId: Int,

    navController: NavController
) {

    val scrollState = rememberScrollState()
    val scope = rememberCoroutineScope()
    var note by remember { mutableStateOf("") }
    var journal by remember { mutableStateOf<JournalEntity?>(null) }

    val viewModel: ReviewViewModel=viewModel()
    LaunchedEffect(journalId) {
        journal = viewModel.getById(journalId)
        note = journal?.note ?: ""
    }

    journal?.let { currentJournal ->
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            "Edit Entry",
                            style = MaterialTheme.typography.titleLarge,
                            color = Charcoal
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Charcoal)
                        }
                    },
                    actions = {
                        // Delete Button as a clean action icon
                        IconButton(onClick = {
                            scope.launch {
                                viewModel.delete(currentJournal)
                                navController.popBackStack()
                            }
                        }) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Delete Note",
                                tint = TerracottaOrange
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = WarmIvory
                    )
                )
            },
            floatingActionButton = {
                // Floating Action Button for Saving (Update) -> Very Material 3
                ExtendedFloatingActionButton(
                    onClick = {
                        scope.launch {
                            viewModel.update(currentJournal.copy(note = note))
                            navController.popBackStack()
                        }
                    },
                    containerColor = SageGreen,
                    contentColor = Color.White,
                    elevation = FloatingActionButtonDefaults.elevation(4.dp)
                ) {
                    Icon(Icons.Default.Check, contentDescription = "Save")
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Save Changes", style = MaterialTheme.typography.labelLarge)
                }
            },
            containerColor = WarmIvory
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .verticalScroll(scrollState)
                    .padding(horizontal = 20.dp, vertical = 12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Professional, rounded image card container
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(240.dp),
                    shape = RoundedCornerShape(24.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(currentJournal.imagePath),
                        contentDescription = "Journal image decoration",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop // Prevents image stretching
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Modern, seamless note editor field
                TextField(
                    value = note,
                    onValueChange = { note = it },
                    placeholder = {
                        Text(
                            "Type your thoughts here...",
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.Gray
                        )
                    },
                    textStyle = MaterialTheme.typography.bodyLarge.copy(color = Charcoal),
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f, fill = false) // Allows text area to expand gracefully
                        .background(Color.Transparent),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent, // Removes the harsh bottom line
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )
            }
        }
    }
}
