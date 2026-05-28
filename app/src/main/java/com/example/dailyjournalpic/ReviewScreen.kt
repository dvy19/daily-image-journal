package com.example.dailyjournalpic


import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun ReviewScreen(
    navController: NavController,
    imagePath: String
) {

    var note by remember {
        mutableStateOf("")
    }

    val currentDate =
        SimpleDateFormat(
            "dd/MM/yyyy",
            Locale.getDefault()
        ).format(Date())

    val currentTime =
        SimpleDateFormat(
            "hh:mm a",
            Locale.getDefault()
        ).format(Date())

    Column {

        Image(
            painter = rememberAsyncImagePainter(

                File(imagePath)
            ),
            contentDescription = null
        )

        Text("Date: $currentDate")

        Text("Time: $currentTime")

        OutlinedTextField(
            value = note,
            onValueChange = {
                note = it
            },
            label = {
                Text("Add Note")
            }
        )

        Button(
            onClick = {

                viewModel.saveJournal(
                    imagePath,
                    note,
                    currentDate,
                    currentTime
                )

                navController.popBackStack(
                    "dashboard",
                    false
                )

            }
        ) {

            Text("Save")
        }
    }
}

@Composable
fun OutlinedTextField(value: String, onValueChange: () -> Unit, label: () -> Unit) {
    TODO("Not yet implemented")
}