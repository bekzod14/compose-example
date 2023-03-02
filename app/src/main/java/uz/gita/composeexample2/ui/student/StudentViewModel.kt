package uz.gita.composeexample2.ui.student

import uz.gita.composeexample2.data.models.GroupFullData
import uz.gita.composeexample2.data.source.room.entities.GroupEntity
import uz.gita.composeexample2.data.source.room.entities.StudentEntity
import uz.gita.composeexample2.ui.common.UIEvent
import uz.xsoft.lesson43.utils.AppViewModel

interface StudentViewModel : AppViewModel<Intent, UiState, UIEffect>

sealed interface UiState {

    object Loading : UiState

    data class Success(
        val students: List<StudentEntity>,
        val group: GroupEntity,
        val dialogState: Boolean = false,
        val editDialog: StudentEntity? = null

    ) :
        UiState

    class Error(val message: String) : UiState
}

sealed interface Intent : UIEvent {
    object Back
    object ShowAddStudentDialog : Intent
    object DismissAddStudentDialog : Intent
    class ShowEditStudentDialog(val student: StudentEntity) :
        Intent

    object DismissEditStudentDialog : Intent
    class SetGroup(val groupFullData: GroupFullData) : Intent

    class Add(val student: StudentEntity) : Intent
    class Edit(val student: StudentEntity) : Intent
    class Delete(val student: StudentEntity) : Intent
}

sealed class UIEffect {
    object BACK
}