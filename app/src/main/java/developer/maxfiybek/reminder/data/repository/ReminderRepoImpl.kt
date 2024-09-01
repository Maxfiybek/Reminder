package developer.maxfiybek.reminder.data.repository

import developer.maxfiybek.reminder.data.db.dao.ReminderDao
import developer.maxfiybek.reminder.data.db.entity.TaskModelEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ReminderRepoImpl @Inject constructor(
    private val dao: ReminderDao
) : ReminderRepository {

    override fun getTasksFromDb(): Flow<List<TaskModelEntity>> = dao.getTasks()

    override suspend fun insertTaskToDb(model: TaskModelEntity) {
        withContext(Dispatchers.IO) {
            dao.insertTask(model)
        }
    }

    override suspend fun deleteTask(model: TaskModelEntity) {
        withContext(Dispatchers.IO) {
            dao.deleteWorks(model)
        }
    }
}