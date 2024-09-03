package developer.maxfiybek.reminder.ui.screens.main

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import developer.maxfiybek.reminder.R
import developer.maxfiybek.reminder.components.EditorDialog
import developer.maxfiybek.reminder.components.SwipeToDelete
import developer.maxfiybek.reminder.components.TodoFloatingActionButton
import developer.maxfiybek.reminder.components.TopBar
import developer.maxfiybek.reminder.components.theme.Primary70
import developer.maxfiybek.reminder.components.theme.TaskTextColor
import developer.maxfiybek.reminder.components.theme.WeakPrimary
import developer.maxfiybek.reminder.data.db.entity.TaskModelEntity
import developer.maxfiybek.reminder.utils.Screens

@SuppressLint("ModifierParameter", "UnusedMaterial3ScaffoldPaddingParameter", "Range")
@Composable
fun MainScreenUi(
    context: Context, navController: NavHostController, modifier: Modifier = Modifier,
) {
    val vm = hiltViewModel<MainScreenViewModel>()
    val tasksFromDb = vm.dataList.collectAsState(initial = emptyList()).value
    var showDialog by remember { mutableStateOf(false) }
    var taskToEdit by remember { mutableStateOf<TaskModelEntity?>(null) }
    Scaffold(topBar = {
        TopBar(
            title = { Text(text = "Tasks") },
        )
    }, floatingActionButton = {
        TodoFloatingActionButton(
            onClick = { navController.navigate(Screens.CreateTask) },
            icon = Icons.Default.Create,
            contentDescription = "Add Task"
        )
    }) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(top = 52.dp)
        ) {
            LazyColumn(
                userScrollEnabled = true,
                modifier = modifier
                    .fillMaxSize()
                    .padding(top = 4.dp),
            ) {
                items(
                    items = tasksFromDb,
                    key = { it }
                ) { model ->
                    SwipeToDelete(
                        item = model,
                        onDelete = {
                            vm.deleteTasks(it)
                        },
                        onEdited = {
                            showDialog = true
                            taskToEdit = it
                        },
                    )
                    {
                        Column(
                            modifier = modifier
                                .fillMaxWidth()
                                .height(100.dp)
                                .padding(10.dp)
                                .clip(RoundedCornerShape(6.dp))
                                .background(WeakPrimary)
                        ) {
                            Row(
                                modifier = modifier
                                    .fillMaxWidth()
                                    .fillMaxHeight(0.75f)
                            ) {
                                Box(
                                    modifier = modifier
                                        .fillMaxHeight()
                                        .fillMaxWidth(0.85f)
                                        .verticalScroll(rememberScrollState())
                                ) {
                                    Text(
                                        fontSize = 14.sp,
                                        overflow = TextOverflow.Ellipsis,
                                        color = TaskTextColor,
                                        maxLines = 4,
                                        modifier = modifier.padding(10.dp),
                                        text = model.tasksToRemind
                                    )
                                }
                                if (model.isImportant) {
                                    Icon(
                                        tint = Red,
                                        modifier = modifier
                                            .padding(18.dp)
                                            .fillMaxSize(),
                                        painter = painterResource(id = R.drawable.ic_important),
                                        contentDescription = "important icon"
                                    )
                                }
                            }
                            Text(
                                color = Primary70,
                                fontSize = 12.sp,
                                modifier = modifier.padding(start = 10.dp, bottom = 4.dp),
                                text = model.dateAndTime
                            )
                        }
                    }
                }
            }
            if (showDialog) {
                taskToEdit?.let {
                    EditorDialog(
                        model = it,
                        onDismiss = { showDialog = false },
                        onConfirm = { editedModel ->
                            vm.updateTask(model = editedModel)
                            Toast.makeText(
                                context,
                                "Task Successfully updated !",
                                Toast.LENGTH_SHORT
                            ).show()
                            showDialog = false
                        }
                    )
                }
            }
        }
    }
}











