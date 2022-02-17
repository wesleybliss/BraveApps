import androidx.compose.material.SnackbarData
import androidx.compose.material.SnackbarDuration

fun snackbarDataOf(
    message: String,
    actionLabel: String? = null,
    duration: SnackbarDuration? = null,
    onDismiss: (() -> Unit)? = null,
    onPerformAction: (() -> Unit)? = null,
) = object : SnackbarData {
    
    override val actionLabel: String? =
        actionLabel
    
    override val duration: SnackbarDuration =
        duration ?: SnackbarDuration.Short
    
    override val message: String =
        message
    
    override fun dismiss() {
        onDismiss?.invoke()
    }
    
    override fun performAction() {
        onPerformAction?.invoke()
    }
    
}

fun showSnackbar(
    message: String,
    actionLabel: String? = "OK",
    duration: SnackbarDuration? = null,
    onDismiss: (() -> Unit)? = null,
    onPerformAction: (() -> Unit)? = null,
) = GlobalState.snackbarChannel.trySend(
    snackbarDataOf(
        message,
        actionLabel,
        duration,
        onDismiss,
        onPerformAction,
    )
)
