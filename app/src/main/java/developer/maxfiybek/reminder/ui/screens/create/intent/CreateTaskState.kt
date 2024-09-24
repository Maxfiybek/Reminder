package developer.maxfiybek.reminder.ui.screens.create.intent

data class CreateTaskState(
    val errorMessage: String = "",
    val tasksToRemind: String = "",
    val isImportant: Boolean = false,
    val dateAndTime: String = "",
)
