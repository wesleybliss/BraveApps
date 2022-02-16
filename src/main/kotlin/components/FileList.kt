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
import models.BraveApp

@Composable
@Preview
fun FileList(items: List<BraveApp>) {
    
    val filteredItems =
        if (GlobalState.filterQuery.isEmpty()) items
        else items.filter {
            val name =
                if (it.data.Name.isNullOrBlank()) it.name
                else it.data.Name!!
            name.lowercase().contains(
                GlobalState.filterQuery.lowercase()
            )
        }
    
    if (items.isNotEmpty()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(all = 0.dp)
        ) {
            
            itemsIndexed(filteredItems) { i, it ->
                val isOpaque: Boolean = (i % 2 == 0)
                FileRow(it, isOpaque)
            }
            
        }
    }
    
}
