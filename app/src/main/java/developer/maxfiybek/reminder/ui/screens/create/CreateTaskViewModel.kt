package developer.maxfiybek.reminder.ui.screens.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import developer.maxfiybek.reminder.data.db.entity.TaskModelEntity
import developer.maxfiybek.reminder.data.repository.ReminderRepository
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateTaskViewModel @Inject constructor(
    private val repository: ReminderRepository
) : ViewModel() {

    fun isTaskValidateToSaveDb(model: TaskModelEntity) = flow {
        if (model.tasksToRemind.isEmpty() || model.tasksToRemind.isBlank()) {
            emit(CreateTaskEvent.Invalidate(message = "Task can't be empty"))
        } else {
            emit(CreateTaskEvent.Validate(model = model))
        }
    }

    fun insertTask(task: TaskModelEntity) {
        viewModelScope.launch {
            repository.insertTaskToDb(task)
        }
    }
}