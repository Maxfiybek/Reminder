package developer.maxfiybek.reminder.ui.screens.create.action_intent_event_state

import androidx.navigation.NavHostController

sealed interface CreateTaskIntent {

    data object OnCreateTask : CreateTaskIntent

    data object OnBackPressed : CreateTaskIntent

    data class OnTaskImportance(val isChecked: Boolean) : CreateTaskIntent

    data class OnTaskTextChange(val task: String) : CreateTaskIntent

//    data class WhenTaskIsEmpty(val errorMessage: String) : CreateTaskIntent
}