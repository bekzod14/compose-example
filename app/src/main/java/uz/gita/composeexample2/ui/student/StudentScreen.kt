package uz.gita.composeexample2.ui.student

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import uz.gita.composeexample2.data.models.GroupFullData
import uz.gita.composeexample2.ui.common.AddItemButton
import uz.gita.composeexample2.ui.common.EmptyListPlaceHolder
import uz.gita.composeexample2.ui.common.Progress


class StudentScreen(val groupFullData: GroupFullData) : AndroidScreen() {

    @Composable
    override fun Content() {
        val viewModel: StudentViewModel = getViewModel<StudentViewModelImpl>()
        viewModel.onEventDispatcher(Intent.SetGroup(groupFullData))
        val uiState = viewModel.collectAsState().value
        StudentScreenContent(uiState = uiState, eventDisPatcher = viewModel::onEventDispatcher)
    }
}

@Composable
fun StudentScreenContent(
    uiState: UiState,
    eventDisPatcher: (Intent) -> Unit
) {
    when (uiState) {
        is UiState.Success -> {
            Box(modifier = Modifier.fillMaxSize()) {
                if (uiState.students.isEmpty()) {
                    EmptyListPlaceHolder(contentItemName = "Student")
                } else {
                    LazyColumn {
                        items(uiState.students) {
                            StudentItem(it, eventDisPatcher)
                            Divider(color = Color.Gray)
                        }

                    }
                }
                AddItemButton(
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.BottomEnd),
                    onClick = { eventDisPatcher(Intent.ShowAddStudentDialog) }
                )

            }
            if (uiState.dialogState)
                AddStudentDialog(uiState.group, eventDisPatcher)
            if (uiState.editDialog != null) {
                EditStudentDialog(
                    student = uiState.editDialog,
                    group = uiState.group,
                    eventDispatcher = eventDisPatcher
                )
            }
        }
        is UiState.Loading -> {
            Progress()
        }
        is UiState.Error -> {
            Toast.makeText(LocalContext.current, uiState.message, Toast.LENGTH_SHORT).show()
        }
    }

}