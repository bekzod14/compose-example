package uz.gita.composeexample2.ui.group

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import uz.gita.composeexample2.data.source.room.entities.GroupEntity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddGroupDialog(
    eventDispatcher: (Intent) -> Unit
) {
    var groupName by remember { mutableStateOf("") }

    Dialog(onDismissRequest = { }) {
        Surface(shape = RoundedCornerShape(10.dp)) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp, bottom = 15.dp)
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    TextField(
                        modifier = Modifier
                            .padding(12.dp)
                            .fillMaxWidth(),
                        value = groupName,
                        onValueChange = { groupName = it },
                        placeholder = { Text(text = "Group Name") },
                        singleLine = true,
                        shape = RoundedCornerShape(8.dp)
                    )
                    Row(
                        Modifier
                            .padding(top = 10.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Button(onClick = { eventDispatcher(Intent.DismissAddGroupDialog) }) {
                            Text(text = "Cancel")
                        }
                        Button(onClick = {
                            eventDispatcher(
                                Intent.Add(
                                    GroupEntity(
                                        0,
                                        groupName
                                    )
                                )
                            )
                        }) {
                            Text(text = "Add")
                        }
                    }
                }
            }
        }
    }
}

@Composable
@Preview
fun AddGroupDialogPreview() {
    AddGroupDialog(eventDispatcher = {})
}