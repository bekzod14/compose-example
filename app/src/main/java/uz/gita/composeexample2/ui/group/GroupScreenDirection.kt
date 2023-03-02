package uz.gita.composeexample2.ui.group

import uz.gita.composeexample2.data.models.GroupFullData

interface GroupScreenDirection {
    suspend fun navigateToStudentScreen(group: GroupFullData)
}