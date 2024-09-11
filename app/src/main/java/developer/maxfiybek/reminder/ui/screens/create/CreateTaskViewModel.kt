package developer.maxfiybek.reminder.ui.screens.create

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import dagger.hilt.android.lifecycle.HiltViewModel
import developer.maxfiybek.reminder.common.UiEvent
import developer.maxfiybek.reminder.data.db.entity.TaskModelEntity
import developer.maxfiybek.reminder.data.repository.ReminderRepository
import developer.maxfiybek.reminder.navigation.Screens
import developer.maxfiybek.reminder.utils.makeToast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateTaskViewModel @Inject constructor(
    private val repository: ReminderRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(CreateTaskState())
    val uiState = _uiState.asStateFlow()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onIntent(intent: CreateTaskIntent) {
        when (intent) {
            is CreateTaskIntent.OnBackPressed -> {
                popBackStack(intent.navController)
            }

            is CreateTaskIntent.OnCreateTask -> {
                createNewTask(context = intent.context)
                sendUiEvent(UiEvent.Navigation(Screens.CreateTask))
            }
        }
    }

    private fun createNewTask(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            if (_uiState.value.tasksToRemind.isNotEmpty() && _uiState.value.tasksToRemind.isNotBlank()) {
                val model = TaskModelEntity(
                    tasksToRemind = _uiState.value.tasksToRemind,
                    isImportant = _uiState.value.isImportant,
                    dateAndTime = _uiState.value.dateAndTime,
                )
                repository.insertTaskToDb(model)
            } else {
                context.makeToast("Task bo`sh bo`la olmaydi!")
            }
        }
    }

    fun onCheckedChange(isChecked: Boolean) {
        _uiState.update { it.copy(isImportant = isChecked) }
    }

    fun onValueChange(value: String) {
        _uiState.update { it.copy(tasksToRemind = value) }
    }

    private fun popBackStack(navController: NavHostController) {
        viewModelScope.launch { navController.popBackStack(Screens.CreateTask, true) }
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch { _uiEvent.send(event) }
    }
}