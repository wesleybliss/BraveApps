
import androidx.compose.material.SnackbarData
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.channels.Channel
import models.BraveApp
import repos.FilesRepo

object GlobalState {
    
    /**
     * Search query for apps list
     */
    var filterQuery by mutableStateOf("")
    
    /**
     * All files found in web app shortcut directories
     */
    var files by mutableStateOf(emptyList<BraveApp>())
    
    /**
     * Files filtered by [filterQuery]
     */
    val filteredFiles get() = FilesRepo.getFilteredFiles()
    
    /**
     * Currently selected app from the list
     */
    var selectedApp by mutableStateOf<BraveApp?>(null)
    
    val hasSelectedApp get() =
        selectedApp?.file != null
    
    val selectedAppName get() =
        selectedApp?.nameOrUnknown ?: "Unknown"
    
    // we allow only one snackbar to be in the queue here, hence conflated
    val snackbarChannel = Channel<SnackbarData>(Channel.CONFLATED)
    
    var dialogConfirmDeleteAppOpen by mutableStateOf(false)
    var dialogConfirmDeleteAppPermanentOpen by mutableStateOf(false)
    var dialogFailedToDeleteAppOpen by mutableStateOf(false)
    
    fun swapDialogs(afterSwap: () -> Unit) {
        dialogConfirmDeleteAppOpen = false
        dialogConfirmDeleteAppPermanentOpen = false
        dialogFailedToDeleteAppOpen = false
        dialogFailedToDeleteAppOpen = false
        afterSwap()
    }
    
}
