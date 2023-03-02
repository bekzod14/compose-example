package uz.gita.composeexample2.ui.student

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
import uz.gita.composeexample2.data.source.room.entities.StudentEntity
import uz.gita.composeexample2.ui.common.DropDown

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditStudentDialog(
    student: StudentEntity,
    group: GroupEntity,
    eventDispatcher: (Intent) -> Unit
) {
    var studentName by remember { mutableStateOf(student.name) }

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
                        value = studentName,
                        onValueChange = { studentName = it },
                        placeholder = { Text(text = "Student Name") },
                        singleLine = true,
                        shape = RoundedCornerShape(8.dp)
                    )

                    TextField(
                        modifier = Modifier
                            .padding(12.dp)
                            .fillMaxWidth(),
                        value = group.name,
                        enabled = false,
                        onValueChange = {},
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
                        Button(onClick = { eventDispatcher(Intent.DismissEditStudentDialog) }) {
                            Text(text = "Cancel")
                        }
                        Button(onClick = {
                            eventDispatcher(
                                Intent.Edit(
                                    StudentEntity(
                                        student.id,
                                        studentName,
                                        group.id
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