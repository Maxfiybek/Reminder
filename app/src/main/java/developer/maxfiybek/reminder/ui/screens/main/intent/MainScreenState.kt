package developer.maxfiybek.reminder.ui.screens.main.intent

import developer.maxfiybek.reminder.data.db.entity.TaskModelEntity

data class MainScreenState(
    val tasksItems: List<TaskModelEntity> = emptyList(),
    val dialogState: Boolean = false,
    val menuState: Boolean = false,
    var tempData: TaskModelEntity? = null
)
