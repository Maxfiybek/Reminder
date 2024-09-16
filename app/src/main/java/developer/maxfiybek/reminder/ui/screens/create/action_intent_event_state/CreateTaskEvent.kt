package developer.maxfiybek.reminder.ui.screens.create.action_intent_event_state

sealed interface CreateTaskEvent {
    data class Error(val errorMessage: String) : CreateTaskEvent
    data object PopBackStack : CreateTaskEvent
}