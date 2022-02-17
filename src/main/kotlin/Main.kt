
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import components.Dialogs
import components.FileDetailsPanel
import components.FilesListPanel
import components.SearchEditText
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow
import repos.FilesRepo

@Composable
@Preview
fun App() {
    
    val snackbarHostState = remember { SnackbarHostState() }
    val scaffoldState = rememberScaffoldState(snackbarHostState = snackbarHostState)
    
    // Read the initial list of files into memory
    FilesRepo.readFiles()
    
    LaunchedEffect(GlobalState.snackbarChannel) {
        GlobalState.snackbarChannel.receiveAsFlow().collect { data ->
            println("snackbar invoked wwith ${data.message}")
            val result = snackbarHostState.showSnackbar(
                message = data.message,
                actionLabel = data.actionLabel
            )
            when (result) {
                SnackbarResult.ActionPerformed -> {
                    /* action has been performed */
                    data.performAction()
                }
                SnackbarResult.Dismissed -> {
                    /* dismissed, no action needed */
                    data.dismiss()
                }
            }
        }
    }
    
    MaterialTheme {
    
        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                TopAppBar(
                    title = { Text("Brave Apps") },
                    actions = {
                        SearchEditText()
                        if (GlobalState.hasSelectedApp)
                            IconButton(onClick = {
                                GlobalState.dialogConfirmDeleteAppOpen = true
                            }) {
                                Icon(Icons.Filled.Delete, contentDescription = "Delete")
                            }
                        IconButton(onClick = { FilesRepo.readFiles() }) {
                            Icon(
                                Icons.Filled.Refresh,
                                contentDescription = "Reload Files",
                                tint = Color.White,
                            )
                        }
                    },
                    
                )
            }
        ) {
    
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                ) {
            
                    FilesListPanel()
                    FileDetailsPanel()
            
                }
        
            }
        }
        
        Dialogs()
        
    }
    
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}
