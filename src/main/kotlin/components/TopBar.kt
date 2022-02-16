package components

import GlobalState
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
@Preview
fun TopBar() {
    
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.2f)
            .padding(all = 8.dp)
    ) {
        
        TextField(
            value = GlobalState.filterQuery,
            onValueChange = {
                GlobalState.filterQuery = it
            },
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            label = { Text("Filter Web Apps") },
            placeholder = { Text("Name...") },
        )
        
    }
    
}
