package developer.maxfiybek.reminder.ui.screens.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import developer.maxfiybek.reminder.data.db.entity.TaskModelEntity
import developer.maxfiybek.reminder.data.repository.ReminderRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val repository: ReminderRepository,
) : ViewModel() {
    val dataList = repository.getTasksFromDb()

    fun deleteTasks(model: TaskModelEntity) {
        viewModelScope.launch {
            repository.deleteTask(model)
        }
    }

    fun editTask(newTask: String, newIsImportant: Boolean, id: Int) {
        viewModelScope.launch {
            println("Updated model -> task: $newTask, important: $newIsImportant, $id")
            repository.editTask(newTask = newTask, newIsImportant = newIsImportant, id = id)
        }
    }

    fun updateTask(model: TaskModelEntity) {
        viewModelScope.launch {
            println("Updated model -> $model")
            repository.updateTask(model)
        }
    }
}