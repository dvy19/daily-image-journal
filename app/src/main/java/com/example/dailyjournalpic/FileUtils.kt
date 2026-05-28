package com.example.dailyjournalpic

import android.content.Context
import java.io.File

fun createImageFile(context: Context): File {

    val dir = context.filesDir

    return File(
        dir,
        "IMG_${System.currentTimeMillis()}.jpg"
    )
}