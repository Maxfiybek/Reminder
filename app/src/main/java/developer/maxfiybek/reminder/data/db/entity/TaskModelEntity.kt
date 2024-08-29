package developer.maxfiybek.reminder.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks_entity")
data class TaskModelEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val tasksToRemind: String,
    val dateAndTime: String,
    val isImportant: Boolean = false
)
