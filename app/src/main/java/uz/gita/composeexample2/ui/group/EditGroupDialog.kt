package uz.gita.composeexample2.ui.group

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import uz.gita.composeexample2.data.models.GroupFullData
import uz.gita.composeexample2.data.source.room.entities.GroupEntity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditGroupDialog(
    group: GroupFullData,
    eventDispatcher: (Intent) -> Unit
) {
    var groupName by remember { mutableStateOf(group.name) }

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
                        Button(onClick = { eventDispatcher(Intent.DismissEditGroupDialog) }) {
                            Text(text = "Cancel")
                        }
                        Button(onClick = {
                            eventDispatcher(
                                Intent.Edit(
                                    GroupEntity(
                                        group.id,
                                        groupName
                                    )
                                )
                            )
                        }) {
                            Text(text = "Edit")
                        }
                    }
                }
            }
        }
    }
}