package developer.maxfiybek.reminder.ui.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import developer.maxfiybek.reminder.data.db.entity.TaskModelEntity
import developer.maxfiybek.reminder.data.repository.MainScreenRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val repository: MainScreenRepository
) : ViewModel() {
    val dataLis = repository.getTasksFromDb()
    fun createTask(entity: TaskModelEntity) {
        viewModelScope.launch {
            repository.insertTaskToDb(entity)
        }
    }
}