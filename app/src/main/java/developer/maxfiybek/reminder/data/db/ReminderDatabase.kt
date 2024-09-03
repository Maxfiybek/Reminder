package developer.maxfiybek.reminder.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import developer.maxfiybek.reminder.data.db.dao.ReminderDao
import developer.maxfiybek.reminder.data.db.entity.TaskModelEntity

@Database(entities = [TaskModelEntity::class], version = 1)
abstract class ReminderDatabase : RoomDatabase() {
    abstract fun reminderDao(): ReminderDao
}