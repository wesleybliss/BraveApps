package components

import GlobalState
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
@Preview
fun FileList() {
    
    val files =
        if (GlobalState.filterQuery.isBlank()) GlobalState.files
        else GlobalState.filteredFiles
    
    if (files.isNotEmpty()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(all = 0.dp)
        ) {
            
            itemsIndexed(files) { i, it ->
                val isOpaque: Boolean = (i % 2 == 0)
                FileRow(it, isOpaque)
            }
            
        }
    }
    
}
