package components

import GlobalState
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
@Preview
fun FileDetailsPanel() {
    
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(all = 16.dp)
            .background(color = Color.White)
    ) {
    
        if (GlobalState.selectedApp == null)
            Text("No app selected")
        else
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    FileActionsToolbar()
                }
                
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Table(GlobalState.selectedApp!!.data.toKeyValuePairs())
                }
                
            }
    }
    
}
