package components

import GlobalState
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
@Preview
fun CheckedFilesPanel() {
    
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = Color.White)
    ) {
        
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.Gray.copy(alpha = 0.2f))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .padding(all = 16.dp),
                ) {
                    Text("${GlobalState.checkedApps.size} apps selected.", )
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .padding(all = 4.dp),
                ) {
                    IconButton(onClick = {
                        GlobalState.dialogConfirmDeleteAllCheckedOpen = true
                    }) {
                        Icon(Icons.Filled.Delete, contentDescription = "Delete All")
                    }
                }
            }
            
            GlobalState.checkedApps.map {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 8.dp)
                ) {
                    Text(it.nameOrUnknown ?: "Unknown")
                }
            }
            
        }
        
    }
    
}

