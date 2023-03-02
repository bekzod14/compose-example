package uz.gita.composeexample2.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.gita.composeexample2.R

@Composable
fun EmptyListPlaceHolder(contentItemName: String) {
    Box(Modifier.fillMaxSize()) {
        Column(
            Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_empty),
                contentDescription = "No Item",
                modifier = Modifier
                    .padding(bottom = 15.dp)
                    .size(100.dp)
            )
            Text(text = "No $contentItemName", fontSize = 18.sp)
        }
    }
}

@Preview
@Composable
fun EmptyListPlaceholderPreview() {
    EmptyListPlaceHolder(contentItemName = "Group")
}