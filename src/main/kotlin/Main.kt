
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import components.Dialogs
import components.FileDetailsPanel
import components.FilesListPanel
import components.SearchEditText

@Composable
@Preview
fun App() {
    
    MaterialTheme {
    
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Brave Apps") },
                    actions = {
                        SearchEditText()
                        if (GlobalState.hasSelectedApp)
                            IconButton(onClick = {
                                // @todo
                                println("@todo delete app " + GlobalState.selectedApp?.data?.Name)
                                GlobalState.dialogConfirmDeleteAppOpen = true
                            }) {
                                Icon(Icons.Filled.Delete, contentDescription = "Localized description")
                            }
                        /*IconButton(onClick = { *//* doSomething() *//* }) {
                            Icon(Icons.Filled.Favorite, contentDescription = "Localized description")
                        }*/
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
