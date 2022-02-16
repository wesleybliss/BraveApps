package components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun Table(items: List<Pair<String, String>>) {
    
    val column1Weight = .3f // 30%
    val column2Weight = .7f // 70%
    
    if (items.isNotEmpty())
        
        LazyColumn(Modifier.fillMaxSize()) {
            // Here is the header
            /*item {
                Row(Modifier.background(Color.Gray)) {
                    TableView.TableCell(text = "Column 1", weight = column1Weight)
                    TableView.TableCell(text = "Column 2", weight = column2Weight)
                }
            }*/
            // Here are all the lines of your table.
            items(items) {
                val (label, value) = it
                Row(Modifier.fillMaxWidth()) {
                    TableCell(text = label, weight = column1Weight)
                    TableCell(text = value, weight = column2Weight)
                }
            }
        }
    
}
