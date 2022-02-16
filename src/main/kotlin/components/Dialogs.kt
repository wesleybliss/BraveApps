package components

import GlobalState
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterialApi::class)
@Composable
@Preview
fun Dialogs() {
    
    if (GlobalState.dialogConfirmDeleteAppOpen)
        AlertDialog(
            title = {
                Text("")
            },
            text = {
                Text(text = "Are you sure you want to delete \"${GlobalState.selectedAppName}\"?")
            },
            onDismissRequest = {
                println("canceled delete conf dialog")                  
            },
            confirmButton = {
                Button(
                    onClick = {
                        println("okgo delete thing")
                    }
                ) {
                    Text("DELETE")
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        println("no no cancel delete")
                    }
                ) {
                    Text("CANCEL")
                }
            }
        )
    
}
