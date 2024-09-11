package developer.maxfiybek.reminder.ui.screens.main

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import developer.maxfiybek.reminder.common.UiEvent
import developer.maxfiybek.reminder.data.db.entity.TaskModelEntity
import developer.maxfiybek.reminder.data.repository.ReminderRepository
import developer.maxfiybek.reminder.enums.MenuType
import developer.maxfiybek.reminder.enums.SwipeType
import developer.maxfiybek.reminder.navigation.Screens
import developer.maxfiybek.reminder.utils.Constants.IntentMenuNavigations
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val repository: ReminderRepository,
) : ViewModel() {

    private val _uiState: MutableStateFlow<MainScreenState> = MutableStateFlow(MainScreenState())
    val uiState = _uiState.asStateFlow()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        viewModelScope.launch {
            repository.getTasksFromDb().collect { tasks ->
                _uiState.update {
                    it.copy(
                        tasksItems = tasks
                    )
                }
            }
        }
    }

    private fun onMenuItemClickCallBack(menuType: MenuType, context: Context, uri: String) {
        when (menuType) {
            MenuType.SOURCE_CODE -> {
                println("Source menu clicked -> $uri")
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
                startActivity(context, intent, null)
            }

            MenuType.CREATOR -> {
                println("Creator menu clicked -> $uri")
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
                startActivity(context, intent, null)
            }
        }
    }

    fun onIntent(intent: MainScreenIntent) {
        when (intent) {
            is MainScreenIntent.OnDialogConfirmedToEdit -> {
                updateTask(intent.model)
                changeDialogState(false)
            }

            is MainScreenIntent.OnDialogDismissed -> {
                changeDialogState(false)
            }

            is MainScreenIntent.OnFloatingButtonClicked -> {
                sendUiEvent(UiEvent.Navigation(Screens.CreateTask))
            }

            is MainScreenIntent.OnMenuShow -> {
                menuStateChanger()
            }

            is MainScreenIntent.OnMenuItemClicked -> {
                when (intent.menuType) {
                    MenuType.SOURCE_CODE -> {
                        onMenuItemClickCallBack(
                            context = intent.context,
                            menuType = intent.menuType,
                            uri = IntentMenuNavigations.SOURCE_CODE
                        )
                    }

                    MenuType.CREATOR -> {
                        onMenuItemClickCallBack(
                            context = intent.context,
                            menuType = intent.menuType,
                            uri = IntentMenuNavigations.CREATOR
                        )
                    }
                }
                menuStateChanger()
            }

            is MainScreenIntent.OnSwiped -> {
                when (intent.swipeType) {
                    SwipeType.FROM_LEFT_TO_RIGHT -> {
                        getTaskById(intent.id)
                        changeDialogState(true)
                    }

                    SwipeType.FROM_RIGHT_TO_LEFT -> {
                        deleteTaskById(intent.id)
                    }
                }
            }
        }
    }

    private fun getTaskById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            uiState.value.tasksItems.find {
                it.id == id
            }?.let { model ->
                _uiState.update { state ->
                    state.copy(
                        tempData = model
                    )
                }
            }
        }
    }

    private fun deleteTaskById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            uiState.value.tasksItems.find { it.id == id }?.let {
                repository.deleteTask(it)
            }
        }
    }

    private fun updateTask(model: TaskModelEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            println("Updated model -> $model")
            repository.updateTask(model)
        }
    }

    private fun changeDialogState(visibility: Boolean) {
        _uiState.update {
            it.copy(
                dialogState = visibility
            )
        }
    }

    private fun menuStateChanger() {
        _uiState.update {
            it.copy(
                menuState = !it.menuState
            )
        }
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch { _uiEvent.send(event) }
    }
}
