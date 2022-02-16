
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import components.FileDetailsPanel
import components.FilesListPanel
import components.TopBar

@Composable
@Preview
fun App() {
    
    MaterialTheme {
    
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Brave Apps") },
                    actions = {
                        // RowScope here, so these icons will be placed horizontally
                        IconButton(onClick = { /* doSomething() */ }) {
                            Icon(Icons.Filled.Favorite, contentDescription = "Localized description")
                        }
                        IconButton(onClick = { /* doSomething() */ }) {
                            Icon(Icons.Filled.Favorite, contentDescription = "Localized description")
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
        
                TopBar()
        
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
    }
    
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}
