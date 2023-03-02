package uz.gita.composeexample2.ui.common

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import uz.gita.composeexample2.ui.group.Intent

@Composable
fun DialogActionButton(text: String, eventDispatcher: (Intent) -> Unit) {
    Button(onClick = { }) {
        Text(text = "Cancel")
    }
}