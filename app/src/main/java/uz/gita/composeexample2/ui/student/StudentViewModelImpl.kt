package uz.gita.composeexample2.ui.student

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import uz.gita.composeexample2.data.repository.GroupRepository
import uz.gita.composeexample2.data.repository.StudentRepository
import javax.inject.Inject

@HiltViewModel
class StudentViewModelImpl @Inject constructor(
    private val studentRepository: StudentRepository,
    private val groupRepository: GroupRepository
) : StudentViewModel, ViewModel() {
    override val container: Container<UiState, UIEffect> = container(UiState.Loading)

    override fun onEventDispatcher(intent: Intent) {
        viewModelScope.launch {
            when (intent) {
                Intent.ShowAddStudentDialog -> {
                    intent {
                        reduce {
                            (container.stateFlow.value as UiState.Success).copy(
                                dialogState = true,
                            )
                        }
                    }
                }
                Intent.DismissAddStudentDialog -> {
                    intent {
                        reduce {
                            (container.stateFlow.value as UiState.Success).copy(
                                dialogState = false
                            )
                        }
                    }
                }
                is Intent.ShowEditStudentDialog -> {
                    intent {
                        reduce {
                            (container.stateFlow.value as UiState.Success).copy(
                                editDialog = intent.student,
                            )
                        }
                    }

                }
                Intent.DismissEditStudentDialog -> {
                    intent {
                        reduce {
                            (container.stateFlow.value as UiState.Success).copy(
                                editDialog = null
                            )
                        }
                    }
                }
                is Intent.Add -> {
                    studentRepository.addStudent(intent.student)
                    intent {
                        reduce {
                            (container.stateFlow.value as UiState.Success).copy(
                                dialogState = false
                            )
                        }
                    }
                }
                is Intent.Edit -> {
                    studentRepository.updateStudent(intent.student)
                    intent {
                        reduce {
                            (container.stateFlow.value as UiState.Success).copy(
                                editDialog = null
                            )
                        }
                    }
                }
                is Intent.Delete -> {
                    studentRepository.deleteStudent(intent.student)
                }
                is Intent.SetGroup -> {
                    studentRepository.getStudents(intent.groupFullData.id).collectLatest {
                        Log.d("RRR", "New Student List")
                        intent {
                            reduce {
                                if (state is UiState.Success) {
                                    (container.stateFlow.value as UiState.Success).copy(
                                        students = it
                                    )
                                } else {
                                    UiState.Success(it, intent.groupFullData.toGroupEntity())
                                }

                            }
                        }
                    }
                }
            }
        }
    }
}