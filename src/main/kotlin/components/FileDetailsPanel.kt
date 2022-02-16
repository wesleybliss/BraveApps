package components

import GlobalState
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
@Preview
fun FileDetailsPanel() {
    
    fun getSelectedAppName() =
        if (GlobalState.selectedApp?.data?.Name.isNullOrBlank()) GlobalState.selectedApp?.name
        else GlobalState.selectedApp!!.data.Name!!
    
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
                Text("App: ${getSelectedAppName()}")
                Table(GlobalState.selectedApp!!.data.toKeyValuePairs())
            }
    }
    
}
