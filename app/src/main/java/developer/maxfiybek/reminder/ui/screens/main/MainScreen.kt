package developer.maxfiybek.reminder.ui.screens.main

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import developer.maxfiybek.reminder.common.UiEvent
import developer.maxfiybek.reminder.components.EditorDialog
import developer.maxfiybek.reminder.components.TodoFloatingActionButton
import developer.maxfiybek.reminder.components.TopBar
import developer.maxfiybek.reminder.enums.MenuType
import developer.maxfiybek.reminder.ui.screens.create.MainScreenIntent
import developer.maxfiybek.reminder.utils.Constants
import developer.maxfiybek.reminder.utils.Screens
import developer.maxfiybek.reminder.utils.makeToast
import kotlinx.coroutines.flow.collectLatest

@SuppressLint("ModifierParameter", "UnusedMaterial3ScaffoldPaddingParameter", "Range")
@Composable
fun MainScreenUi(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val viewModel = hiltViewModel<MainScreenViewModel>()
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.uiEvent.collectLatest {
            when (it) {
                is UiEvent.Navigation -> {
                    navController.navigate(it.screens)
                }
            }
        }
    }

    Scaffold(
        topBar = {
            TopBar(
                title = { Text(text = "Tasks") },
                actions = {
                    MainScreenMenu(
                        isShowMenu = uiState.menuState,
                        onShowOption = { viewModel.onIntent(MainScreenIntent.OnMenuShow) },
                        onItemClicked = {
                            when (it) {
                                MenuType.SOURCE_CODE -> {
                                    println("Source code clicked")
                                    viewModel.onIntent(
                                        MainScreenIntent.OnMenuItemClicked(
                                            menuType = MenuType.SOURCE_CODE,
                                            context = context,
                                            uri = Constants.IntentMenuNavigations.SOURCE_CODE
                                        )
                                    )
                                }

                                MenuType.CREATOR -> {
                                    viewModel.onIntent(
                                        MainScreenIntent.OnMenuItemClicked(
                                            menuType = MenuType.CREATOR,
                                            context = context,
                                            uri = Constants.IntentMenuNavigations.CREATOR
                                        )
                                    )
                                }
                            }
                        }
                    )
                }
            )
        },
        floatingActionButton = {
            TodoFloatingActionButton(
                onClick = { navController.navigate(Screens.CreateTask) },
                icon = Icons.Default.Create,
                contentDescription = "Add Task"
            )
        },
        content = { paddingValues ->
            MainScreenContent(
                tasksFromDb = uiState.tasksItems,
                paddingValues = paddingValues,
                onSwipe = { swipeType, id ->
                    viewModel.onIntent(MainScreenIntent.OnSwiped(swipeType, id))
                }
            )
            if (uiState.dialogState) {
                uiState.tempData?.let {
                    EditorDialog(
                        model = it,
                        onDismiss = { viewModel.onIntent(MainScreenIntent.OnDialogDismissed) },
                        onConfirm = { editedModel ->
                            viewModel.onIntent(
                                MainScreenIntent.OnDialogConfirmedToEdit(
                                    editedModel
                                )
                            )
                            context.makeToast("Model successfully edited")
                        }
                    )
                }
            }
        }
    )
}










