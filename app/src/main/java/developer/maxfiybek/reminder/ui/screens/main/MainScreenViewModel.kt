package developer.maxfiybek.reminder.ui.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import developer.maxfiybek.reminder.data.db.entity.TaskModelEntity
import developer.maxfiybek.reminder.data.repository.ReminderRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val repository: ReminderRepository
) : ViewModel() {
    val dataList = repository.getTasksFromDb()
    fun deleteTasks(model: TaskModelEntity) {
        viewModelScope.launch {
            repository.deleteTask(model)
        }
    }

    fun updateTask(model: TaskModelEntity) {
        viewModelScope.launch {
            repository.updateTask(model = model)
        }
    }
}