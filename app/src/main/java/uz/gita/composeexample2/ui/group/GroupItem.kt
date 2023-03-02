package uz.gita.composeexample2.ui.group

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import uz.gita.composeexample2.R
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.gita.composeexample2.data.models.GroupFullData

@Composable
fun GroupItem(
    group: GroupFullData,
    eventDispatcher: (Intent) -> Unit
) {
    Box(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .clickable(enabled = true) {
                Log.d("RRR", "Group Item Clicked")
                eventDispatcher(Intent.OpenStudentsScreen(group))
            }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.padding(start = 8.dp, top = 10.dp, bottom = 10.dp)) {
                Column(Modifier.padding(0.dp, bottom = 5.dp)) {
                    TitleText(modifier = Modifier, text = "Group Name")
                    TitleContentText(modifier = Modifier, text = group.name)

                }
                Column(Modifier.padding(0.dp, top = 5.dp)) {
                    TitleText(modifier = Modifier, text = "Students Count")
                    TitleContentText(modifier = Modifier, text = group.count.toString())
                }
            }
            Column(
                modifier = Modifier.padding(end = 8.dp),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                ActionButton(
                    icon = R.drawable.ic_edit,
                    onClick = { eventDispatcher(Intent.ShowEditGroupDialog(group)) },
                    modifier = Modifier.padding(4.dp),
                    color = Color.Yellow
                )
                ActionButton(
                    icon = R.drawable.ic_delete,
                    onClick = { eventDispatcher(Intent.Delete(group.toGroupEntity())) },
                    modifier = Modifier.padding(4.dp),
                    color = Color.Red
                )
            }
        }
    }
}

@Composable
fun TitleText(text: String, modifier: Modifier) {
    Text(
        text = text,
        modifier = modifier,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        color = Color.Gray
    )
}

@Composable
fun TitleContentText(text: String, modifier: Modifier) {
    Text(
        text = text,
        modifier = modifier,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        color = Color.Black
    )
}

@Composable
fun ActionButton(
    icon: Int,
    modifier: Modifier,
    color: Color,
    onClick: () -> Unit,
    shape: Shape = RectangleShape
) {
    IconButton(
        onClick = onClick, modifier = modifier
            .clip(shape)
            .background(color)
    ) {
        Icon(painter = painterResource(id = icon), contentDescription = "icon bor")
    }
}

@Composable
@Preview
fun groupItemPreview() {
    GroupItem(group = GroupFullData(1, "Android", 12), {})
}
