package developer.maxfiybek.reminder.data.repository

import developer.maxfiybek.reminder.data.db.dao.ReminderDao
import developer.maxfiybek.reminder.data.db.entity.TaskModelEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MainScreenRepoImpl @Inject constructor(
    private val dao: ReminderDao
) : MainScreenRepository {

    override fun getTasksFromDb(): Flow<List<TaskModelEntity>> = dao.getTasks()

    override suspend fun insertTaskToDb(model: TaskModelEntity) {
        dao.insertWorks(model)
    }
}