package developer.maxfiybek.reminder.ui.screens.create.action_intent_event_state

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import developer.maxfiybek.reminder.R
import developer.maxfiybek.reminder.common.UiEvent
import developer.maxfiybek.reminder.components.TodoFloatingActionButton
import developer.maxfiybek.reminder.components.TopBar
import developer.maxfiybek.reminder.components.TopBarIconButton
import developer.maxfiybek.reminder.ui.screens.create.CreateTaskContent
import developer.maxfiybek.reminder.ui.screens.create.CreateTaskViewModel
import developer.maxfiybek.reminder.utils.makeToast

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CreateTaskScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val viewModel = hiltViewModel<CreateTaskViewModel>()
    val screenState by viewModel.uiState.collectAsStateWithLifecycle()
    val errorMessage = stringResource(id = R.string.str_task_when_empty)
    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect {
            when (it) {
                is UiEvent.Error -> {
                    context.makeToast(errorMessage)
                }

                is UiEvent.PopBackStack -> {
                    navController.popBackStack()
                }
                else -> Unit
            }
        }
    }
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Scaffold(
            topBar = {
                TopBar(
                    navigationIcon = {
                        TopBarIconButton(
                            imageVectorFromDrawable = R.drawable.ic_back_simple,
                            onClick = { viewModel.onIntent(CreateTaskIntent.OnBackPressed) },
                            contentDescription = "Back"
                        )
                    },
                    title = { Text(text = stringResource(id = R.string.str_tasks)) },
                )
            },
            content = {
                CreateTaskContent(
                    paddingValues = it,
                    onCheckedChange = { onCheckedChange ->
                        viewModel.onIntent(CreateTaskIntent.OnTaskImportance(onCheckedChange))
                    },
                    onTaskValueChange = { onTaskValueChange ->
                        viewModel.onIntent(CreateTaskIntent.OnTaskTextChange(onTaskValueChange))
                    },
                    createTaskState = screenState
                )
            },
            floatingActionButton = {
                TodoFloatingActionButton(
                    onClick = {
                        viewModel.onIntent(CreateTaskIntent.OnCreateTask)
                    },
                    icon = Icons.Default.Add,
                    contentDescription = "Add Task"
                )
            }
        )
    }
}