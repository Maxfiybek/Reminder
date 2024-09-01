package developer.maxfiybek.reminder.data.db.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "tasks_entity")
data class TaskModelEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val tasksToRemind: String,
    val dateAndTime: String,
    val isImportant: Boolean = false
) : Parcelable
