package developer.maxfiybek.reminder.ui.screens.create

import developer.maxfiybek.reminder.data.db.entity.TaskModelEntity

sealed class CreateTaskEvent {
    data class Validate(val model: TaskModelEntity) : CreateTaskEvent()
    data class Invalidate(val message: String) : CreateTaskEvent()
}