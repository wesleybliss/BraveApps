package models

import GlobalState
import java.io.File

data class BraveApp(
    val name: String,
    val file: File,
    val data: DesktopFileData,
) {
    
    val nameOrUnknown get () =
        if (GlobalState.selectedApp?.data?.Name.isNullOrBlank()) GlobalState.selectedApp?.name
        else
            if (GlobalState.selectedApp!!.data.Name.isNullOrBlank()) "Unknown"
            else GlobalState.selectedApp!!.data.Name!!
    
}
