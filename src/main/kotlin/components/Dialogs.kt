package components

import GlobalState
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import repos.FilesRepo
import showSnackbar

@Composable
@Preview
fun Dialogs() {
    
    if (GlobalState.dialogConfirmDeleteAppOpen)
        ConfirmationDialog(
            title = "Delete Web App",
            message = "Are you sure you want to delete \"${GlobalState.selectedAppName}\"?",
            closeDialog = {
                GlobalState.swapDialogs {
                    GlobalState.dialogConfirmDeleteAppOpen = false
                }
            },
            onConfirm = {
                FilesRepo.deleteFile(GlobalState.selectedApp!!.file)
                showSnackbar("Deleted \"${GlobalState.selectedAppName}\"")
                GlobalState.selectedApp = null
                GlobalState.filterQuery = ""
                FilesRepo.readFiles()
            },
            confirmText = "DELETE",
        )
    
    if (GlobalState.dialogConfirmDeleteAppPermanentOpen)
        ConfirmationDialog(
            title = "Permanently Delete Web App",
            message = "File can't be moved to the trash. Delete permanently?",
            closeDialog = {
                GlobalState.swapDialogs {
                    GlobalState.dialogConfirmDeleteAppPermanentOpen = false
                }
            },
            onConfirm = {
                FilesRepo.deleteFile(GlobalState.selectedApp!!.file, permanent = true)
                showSnackbar("Deleted \"${GlobalState.selectedAppName}\"")
                GlobalState.selectedApp = null
                GlobalState.filterQuery = ""
                FilesRepo.readFiles()
            },
            confirmText = "DELETE",
        )
    
    if (GlobalState.dialogFailedToDeleteAppOpen)
        ConfirmationDialog(
            title = "Failed to Delete Web App",
            message = "File can't be deleted. Aborting.",
            closeDialog = {
                GlobalState.swapDialogs {
                    GlobalState.dialogFailedToDeleteAppOpen = false
                }
            },
            onConfirm = {
                GlobalState.dialogFailedToDeleteAppOpen = false
                showSnackbar("Failed to delete app \"${GlobalState.selectedAppName}\"")
            },
            cancelText = null,
        )
    
}
