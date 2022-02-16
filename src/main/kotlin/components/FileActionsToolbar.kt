package components

import GlobalState
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
@Preview
fun FileActionsToolbar() {
    
    fun handleDeleteClick() {
        println("@todo delete " + (GlobalState.selectedApp?.data?.Name ?: ""))
    }
    
    if (GlobalState.selectedApp != null)
        Row(
            
        ) {
            
            Column(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(GlobalState.selectedApp?.nameOrUnknown ?: "Unknown")
            }
            
            Column(
                modifier = Modifier.width(intrinsicSize = IntrinsicSize.Min)
            ) {
                IconButton(
                    content = {
                        /*Icon(FontAwesomeIcons.Solid.Trash, "")*/
                        Icon(
                            Icons.Filled.Delete,
                            contentDescription = "Favorite",
                            modifier = Modifier.size(ButtonDefaults.IconSize)
                        )
                    },
                    modifier = Modifier
                        .width(20.dp)
                        .height(20.dp),
                    onClick = {
                        handleDeleteClick()
                    }
                )
            }
        }
    
}
