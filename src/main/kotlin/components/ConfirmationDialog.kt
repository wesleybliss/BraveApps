package components

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterialApi::class)
@Composable
@Preview
fun ConfirmationDialog(
    title: String,
    message: String,
    closeDialog: () -> Unit,
    onConfirm: () -> Unit,
    confirmText: String = "OK",
    cancelText: String? = "CANCEL",
) {
    
    AlertDialog(
        title = { Text(title) },
        text = { Text(text = message) },
        onDismissRequest = closeDialog,
        confirmButton = {
            Button(onClick = {
                onConfirm()
                closeDialog()
            }) {
                Text(confirmText)
            }
        },
        dismissButton = {
            if (!cancelText.isNullOrEmpty())
                Button(onClick = closeDialog) {
                    Text(cancelText)
                }
        }
    )
    
}
