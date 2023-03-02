package uz.gita.composeexample2.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import uz.gita.composeexample2.data.models.GroupFullData
import uz.gita.composeexample2.data.source.room.entities.GroupEntity


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDown(
    options: List<GroupEntity>,
    onSelection: (GroupEntity) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedOptionData by remember { mutableStateOf(options[0]) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {
        TextField(
            readOnly = true,
            value = selectedOptionData.name,
            onValueChange = { },
            label = { Text("Group") },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded
                )
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors()
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }
        ) {
            options.forEach { selectionOption ->
                DropdownMenuItem(
                    text = {
                        Text(text = selectionOption.name)
                    },
                    onClick = {
                        selectedOptionData = selectionOption
                        onSelection(selectedOptionData)
                        expanded = false
                    })

            }
        }
    }
}