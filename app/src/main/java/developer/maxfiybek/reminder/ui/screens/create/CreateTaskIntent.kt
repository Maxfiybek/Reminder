package developer.maxfiybek.reminder.ui.screens.create

import android.content.Context
import androidx.navigation.NavHostController

sealed interface CreateTaskIntent {
//    data class OnCreateNewTask(
//        val task: String,
//        val isImportant: Boolean,
//        val dateAndTime: String,
//        val context: Context,
//    ) : CreateTaskIntent

    data class OnCreateTask(val context: Context, val navController: NavHostController) : CreateTaskIntent

    data class OnBackPressed(val navController: NavHostController) : CreateTaskIntent

}