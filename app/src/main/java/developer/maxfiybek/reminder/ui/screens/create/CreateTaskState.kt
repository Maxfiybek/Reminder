package developer.maxfiybek.reminder.ui.screens.create

data class CreateTaskState(
    val tasksToRemind: String = "",
    val isImportant: Boolean = false,
    val dateAndTime: String = "",
)
