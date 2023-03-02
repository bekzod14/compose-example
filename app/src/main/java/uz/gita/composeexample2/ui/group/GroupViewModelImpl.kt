package uz.gita.composeexample2.ui.group

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import uz.gita.composeexample2.data.repository.GroupRepository
import uz.gita.composeexample2.data.source.room.entities.GroupEntity
import javax.inject.Inject

@HiltViewModel
class GroupViewModelImpl @Inject constructor(
    private val repository: GroupRepository,
    private val directions: GroupScreenDirection
) : GroupViewModel, ViewModel() {

    override val container: Container<UiState, SideEffect> = container(UiState.Loading)

    init {
        repository.getGroups().onEach {
            intent {
                reduce {
                    UiState.Success(it)
                }
            }
        }.launchIn(viewModelScope)

    }

    override fun onEventDispatcher(intent: Intent) {
        viewModelScope.launch {
            when (intent) {
                Intent.ShowAddGroupDialog -> {
                    intent {
                        reduce {
                            (container.stateFlow.value as UiState.Success).copy(dialogState = true)
                        }
                    }
                }
                is Intent.DismissAddGroupDialog -> {
                    intent {
                        reduce {
                            (container.stateFlow.value as UiState.Success).copy(dialogState = false)
                        }
                    }
                }
                is Intent.ShowEditGroupDialog -> {
                    intent {
                        reduce {
                            (container.stateFlow.value as UiState.Success).copy(editDialog = intent.group)
                        }
                    }

                }
                is Intent.DismissEditGroupDialog -> {
                    intent {
                        reduce {
                            (container.stateFlow.value as UiState.Success).copy(editDialog = null)
                        }
                    }
                }
                is Intent.Add -> {
                    repository.addGroup(intent.group)
                    intent {
                        reduce {
                            (container.stateFlow.value as UiState.Success).copy(dialogState = false)
                        }
                    }
                }
                is Intent.Edit -> {
                    repository.updateGroup(intent.group)
                    intent {
                        reduce {
                            (container.stateFlow.value as UiState.Success).copy(editDialog = null)
                        }
                    }
                }
                is Intent.Delete -> {
                    repository.deleteGroup(intent.group)
                }
                is Intent.OpenStudentsScreen -> {
                    directions.navigateToStudentScreen(intent.group)
                }
            }
        }

    }
}