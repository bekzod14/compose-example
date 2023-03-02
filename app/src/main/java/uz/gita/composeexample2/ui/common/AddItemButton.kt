package uz.gita.composeexample2.ui.common

import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import uz.gita.composeexample2.R
import uz.gita.composeexample2.ui.group.Intent

@Composable
fun AddItemButton(
    modifier: Modifier,
    onClick: () -> Unit
) {
    FloatingActionButton(
        modifier = modifier,
        onClick = {
            onClick()
        }) {
        Icon(
            painter = painterResource(id = R.drawable.ic_baseline_add_24),
            contentDescription = "Add Item"
        )
    }
}

@Composable
@Preview
fun AddItemButtonPreview() {
    AddItemButton(modifier = Modifier, onClick = {})
}