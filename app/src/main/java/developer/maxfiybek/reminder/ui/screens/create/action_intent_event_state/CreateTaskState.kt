package developer.maxfiybek.reminder.ui.screens.create.action_intent_event_state

data class CreateTaskState(
    val errorMessage: String = "",
    val tasksToRemind: String = "",
    val isImportant: Boolean = false,
    val dateAndTime: String = "",
)
