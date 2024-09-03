package developer.maxfiybek.reminder.models

import kotlinx.serialization.Serializable

@Serializable
data class TaskUiModel(
    val taskToDo: String?,
    val taskDate: String?,
    val isImportant: Boolean
)
