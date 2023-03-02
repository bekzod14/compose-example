package uz.gita.composeexample2.ui.group

import uz.gita.composeexample2.data.models.GroupFullData
import uz.gita.composeexample2.data.source.room.entities.GroupEntity
import uz.gita.composeexample2.ui.common.UIEvent
import uz.gita.composeexample2.ui.student.UIEffect
import uz.xsoft.lesson43.utils.AppViewModel

interface GroupViewModel : AppViewModel<Intent, UiState, SideEffect>

sealed interface UiState {
    object Loading : UiState
    data class Success(
        val groups: List<GroupFullData>,
        val dialogState: Boolean = false,
        val editDialog: GroupFullData? = null

    ) :
        UiState

    class Error(val message: String) : UiState
}

sealed interface Intent : UIEvent {
    object ShowAddGroupDialog : Intent
    object DismissAddGroupDialog : Intent

    class ShowEditGroupDialog(val group: GroupFullData) : Intent
    object DismissEditGroupDialog : Intent

    class OpenStudentsScreen(val group: GroupFullData) : Intent

    class Add(val group: GroupEntity) : Intent
    class Edit(val group: GroupEntity) : Intent
    class Delete(val group: GroupEntity) : Intent
}

enum class SideEffect {
    CLOSE, ADD_DIALOG
}