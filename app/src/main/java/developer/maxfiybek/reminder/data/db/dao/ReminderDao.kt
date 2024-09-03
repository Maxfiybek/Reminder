package developer.maxfiybek.reminder.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import developer.maxfiybek.reminder.data.db.entity.TaskModelEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ReminderDao {
    @Insert
    suspend fun insertTask(model: TaskModelEntity)

    @Delete
    suspend fun deleteWorks(model: TaskModelEntity)

    @Query("SELECT * FROM tasks_entity")
    fun getTasks(): Flow<List<TaskModelEntity>>

    @Query("Update tasks_entity Set tasksToRemind = :newTask, isImportant = :newIsImportant Where id = :id")
    suspend fun editTask(newTask: String, newIsImportant: Boolean, id: Int)

    @Update
    suspend fun updateTask(model: TaskModelEntity)
}