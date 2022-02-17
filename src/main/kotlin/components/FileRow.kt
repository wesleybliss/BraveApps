package components

import GlobalState
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import models.BraveApp

@Composable
@Preview
fun FileRow(file: BraveApp, opaqueBackground: Boolean = false) {
    
    val label =
        if (file.data.Name.isNullOrBlank()) file.name
        else file.data.Name!!
    
    val backgroundColor =
        if (GlobalState.selectedApp?.name == file.name)
            Color.Blue.copy(alpha = 0.3f)
        else
            if (opaqueBackground) Color.Gray.copy(alpha = 0.2f)
            else Color.Transparent
    
    fun setAppChecked(checked: Boolean) {
        
        if (checked)
            GlobalState.checkedApps = GlobalState.checkedApps
                .toMutableList()
                .apply { add(file) }
                .toList()
        else
            GlobalState.checkedApps = GlobalState.checkedApps.filter { it != file }
        
        // println("Checked apps: ${GlobalState.checkedApps}")
        
    }
    
    fun handleItemClick() {
    
        if (GlobalState.multiselectEnabled)
            setAppChecked(!GlobalState.checkedApps.contains(file))
        else
            GlobalState.selectedApp = file
        
    }
    
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 0.dp)
            .background(color = backgroundColor)
            .clickable { handleItemClick() },
    ) {
        
        Column(
            modifier = Modifier.fillMaxWidth(0.9f)
        ) {
            Text(
                text = AnnotatedString(label),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 16.dp),
            )
        }
    
        if (GlobalState.multiselectEnabled)
            Column(
                modifier = Modifier.fillMaxWidth(0.1f)
            ) {
                Checkbox(
                    checked = GlobalState.checkedApps.contains(file),
                    onCheckedChange = {
                        setAppChecked(it)
                    }
                )
            }
        
    }
    
}
