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

/*
==============================================================================================

creates a new image file location in device storage, before cameraX saves image

==============================================================================================

context

handle system environment, bridge between app and the android system

gives access to, app storage, resources, database, preferences, system services like camera

ActivityContext = tied to single screen,  like toast
can be written as this.
like showing dialogs, UI related

ApplicationContext lies for whole app, like database or room
for background tasks

If you use Activity Context for long-lived objects (like a singleton database),
you risk leaks because the Activity might be destroyed but still referenced.

If you use Application Context for UI tasks (like showing a dialog),
it won’t work because it doesn’t know about the current screen.

==============================================================================================

filesDir

app's internal file storage directory private
/data/data/com.example.app/files/

secure, auto delete on uninstall
no storage permission needed.

==============================================================================================

"IMG_${System.currentTimeMillis()}.jpg"

kotlin string interpolation "{}" => used to store dynamic values

use time in milliseconds to keep image file name unique

.jpg or .jpeg cameraX stores file in compressed format
when camera captures the photo, raw camera image size is very large.

jpeg reduces image size making faster loading

Lossy compression = jpeg removes tiny invisible image details to reduce the file size
Lossless compression = png keeps all the image details

 */