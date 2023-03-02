package uz.gita.composeexample2.ui.group

import android.util.Log
import uz.gita.composeexample2.data.models.GroupFullData
import uz.gita.composeexample2.navigator.AppNavigator
import uz.gita.composeexample2.ui.student.StudentScreen
import javax.inject.Inject

class GroupScreenDirectionImpl @Inject constructor(
    private val appNavigator: AppNavigator
) : GroupScreenDirection {
    override suspend fun navigateToStudentScreen(group: GroupFullData) {
        Log.d("RRR", "Navigate to Student Screen")
        appNavigator.navigateTo(StudentScreen(group))
    }
}