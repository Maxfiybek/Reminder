package developer.maxfiybek.reminder.ui.screens.create.intent

sealed interface CreateTaskIntent {

    data object OnCreateTask : CreateTaskIntent

    data object OnBackPressed : CreateTaskIntent

    data class OnTaskImportance(val isChecked: Boolean) : CreateTaskIntent

    data class OnTaskTextChange(val task: String) : CreateTaskIntent
}