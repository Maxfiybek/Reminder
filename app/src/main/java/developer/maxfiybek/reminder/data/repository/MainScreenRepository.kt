package developer.maxfiybek.reminder.data.repository

import developer.maxfiybek.reminder.data.db.entity.TaskModelEntity
import kotlinx.coroutines.flow.Flow

interface MainScreenRepository {
    fun getTasksFromDb(): Flow<List<TaskModelEntity>>
    suspend fun insertTaskToDb(model: TaskModelEntity)
}