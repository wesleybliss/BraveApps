package models

import java.io.File

data class BraveApp(
    val name: String,
    val file: File,
    val data: DesktopFileData,
)
