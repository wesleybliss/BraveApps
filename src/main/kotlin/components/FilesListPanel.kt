package components

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import repos.FilesRepo

@Composable
@Preview
fun FilesListPanel() {
    
    val filesState = remember { FilesRepo.files }
    
    Column(
        modifier = Modifier
            .fillMaxWidth(0.5f)
            .fillMaxHeight()
            .background(color = Color.Gray.copy(alpha = 0.1f))
    ) {
        
        Row {
            FileList(filesState)
        }
        
    }
    
}
