package uz.gita.composeexample2.ui.group

import android.util.Log
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
import kotlinx.coroutines.flow.collectLatest
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect
import uz.gita.composeexample2.ui.common.AddItemButton
import uz.gita.composeexample2.ui.common.EmptyListPlaceHolder
import uz.gita.composeexample2.ui.common.Progress

class GroupScreen : AndroidScreen() {

    @Composable
    override fun Content() {
        val viewModel: GroupViewModel = getViewModel<GroupViewModelImpl>()
        val uiState = viewModel.collectAsState().value
        Log.d("EEEEE", "Content")
        GroupScreenContent(uiState = uiState, eventDispatcher = viewModel::onEventDispatcher)
    }

}

@Composable
fun GroupScreenContent(
    uiState: UiState,
    eventDispatcher: (Intent) -> Unit
) {
    when (uiState) {
        is UiState.Success -> {
            Box(modifier = Modifier.fillMaxSize()) {

                if (uiState.groups.isEmpty()) {
                    EmptyListPlaceHolder(contentItemName = "Group")

                } else {
                    LazyColumn {
                        items(uiState.groups) {
                            GroupItem(group = it, eventDispatcher)
                            Divider(color = Color.Gray)
                        }

                    }
                }

                AddItemButton(
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.BottomEnd),
                    onClick = { eventDispatcher(Intent.ShowAddGroupDialog) }
                )



            }
            if (uiState.dialogState)
                AddGroupDialog(eventDispatcher = eventDispatcher)
            if (uiState.editDialog != null) {
                EditGroupDialog(group = uiState.editDialog, eventDispatcher = eventDispatcher)
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