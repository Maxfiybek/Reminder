package developer.maxfiybek.reminder.ui.screens.create

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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import developer.maxfiybek.reminder.R
import developer.maxfiybek.reminder.common.UiEvent
import developer.maxfiybek.reminder.components.TodoFloatingActionButton
import developer.maxfiybek.reminder.components.TopBar
import developer.maxfiybek.reminder.components.TopBarIconButton

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CreateTaskScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val viewModel = hiltViewModel<CreateTaskViewModel>()
    val screenState by viewModel.uiState.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect {
            when (it) {
                is UiEvent.Navigation -> {
                    navController.popBackStack(it.screens, inclusive = true)
                }
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
                            onClick = {
                                viewModel.onIntent(
                                    CreateTaskIntent.OnBackPressed(navController = navController)
                                )
                            }, contentDescription = "Back"
                        )
                    },
                    title = { Text(text = "Tasks") },
                )
            },
            content = {
                CreateTaskContent(
                    paddingValues = it,
                    onCheckedChange = viewModel::onCheckedChange,
                    onValueChange = viewModel::onValueChange,
                    createTaskState = screenState
                )
            },
            floatingActionButton = {
                TodoFloatingActionButton(
                    onClick = {
                        viewModel.onIntent(
                            CreateTaskIntent.OnCreateTask(
                                context = context,
                                navController = navController
                            )
                        )
                    },
                    icon = Icons.Default.Add,
                    contentDescription = "Add Task"
                )
            }
        )
    }
}

