
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import models.BraveApp

object GlobalState {
    
    /**
     * Search query for apps list
     */
    var filterQuery by mutableStateOf("")
    
    /**
     * Currently selected app from the list
     */
    var selectedApp by mutableStateOf<BraveApp?>(null)
    
}
