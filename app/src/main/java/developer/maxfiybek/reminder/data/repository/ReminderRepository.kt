package developer.maxfiybek.reminder.data.repository

import developer.maxfiybek.reminder.data.db.entity.TaskModelEntity
import kotlinx.coroutines.flow.Flow

interface ReminderRepository {
    fun getTasksFromDb(): Flow<List<TaskModelEntity>>
    suspend fun insertTaskToDb(model: TaskModelEntity)
    suspend fun deleteTask(model: TaskModelEntity)
    suspend fun updateTask(model: TaskModelEntity)
    suspend fun editTask(newTask: String, newIsImportant: Boolean,id: Int)
}