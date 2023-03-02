package uz.gita.composeexample2.ui.student

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uz.gita.composeexample2.R
import uz.gita.composeexample2.data.source.room.entities.GroupEntity
import uz.gita.composeexample2.data.source.room.entities.StudentEntity
import uz.gita.composeexample2.ui.group.ActionButton
import uz.gita.composeexample2.ui.group.TitleContentText

@Composable
fun StudentItem(
    student: StudentEntity,
    eventDispatcher: (Intent) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.img),
                contentScale = ContentScale.Fit,
                contentDescription = "profile",
                modifier = Modifier
                    .padding(8.dp)
                    .size(60.dp)
                    .clip(CircleShape),
            )
            TitleContentText(text = student.name, modifier = Modifier)
            Spacer(modifier = Modifier.weight(1f))
            ActionButton(
                icon = R.drawable.ic_edit,
                modifier = Modifier.padding(5.dp),
                color = Color.Yellow,
                shape = CircleShape,
                onClick = { eventDispatcher(Intent.ShowEditStudentDialog(student)) })
            ActionButton(
                icon = R.drawable.ic_delete,
                modifier = Modifier.padding(end = 8.dp),
                color = Color.Red,
                shape = CircleShape,
                onClick = { eventDispatcher(Intent.Delete(student)) })
        }
    }

}

@Composable
@Preview
fun StudentItemPreview() {
    StudentItem(student = StudentEntity(1, "Ixtiyorjon", 1), {})
}