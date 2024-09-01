package developer.maxfiybek.reminder.ui.screens.create

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import developer.maxfiybek.reminder.R
import developer.maxfiybek.reminder.components.TodoFloatingActionButton
import developer.maxfiybek.reminder.components.TopBar
import developer.maxfiybek.reminder.components.TopBarIconButton
import developer.maxfiybek.reminder.components.theme.Primary70
import developer.maxfiybek.reminder.data.db.entity.TaskModelEntity
import developer.maxfiybek.reminder.utils.Screens
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CreateTaskScreen(
    context: Context,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val currentTime = Date()
    val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    val formattedDate = formatter.format(currentTime)
    val vm = hiltViewModel<CreateTaskViewModel>()
    var text by remember { mutableStateOf("") }
    var isChecked by remember { mutableStateOf(false) }
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
                                navController.popBackStack(Screens.CreateTask, true)
                            }, contentDescription = "Back"
                        )
                    },
                    title = { Text(text = "Tasks") },
                )
            },
            floatingActionButton = {
                TodoFloatingActionButton(
                    onClick = {
                        println("onClick")
                        vm.isTaskValidateToSaveDb(
                            TaskModelEntity(
                                tasksToRemind = text,
                                isImportant = isChecked,
                                dateAndTime = formattedDate
                            )
                        ).onEach {
                            when (it) {
                                is CreateTaskEvent.Invalidate -> {
                                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                                }

                                is CreateTaskEvent.Validate -> {
                                    println("validate")
                                    vm.insertTask(it.model)
                                    navController.popBackStack(Screens.CreateTask, true)
                                }
                            }
                        }.launchIn(vm.viewModelScope)
                    },
                    icon = Icons.Default.Add,
                    contentDescription = "Add Task"
                )
            }
        ) {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(top = 52.dp)
            ) {
                TextField(
                    value = text,
                    onValueChange = { text = it },
                    label = { Text(text = "To do") },
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 10.dp),
                    singleLine = true,
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = Primary70,
                        unfocusedTextColor = Primary70,
                        cursorColor = Primary70
                    )
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = modifier
                        .padding(top = 10.dp)
                        .fillMaxWidth()
                        .height(30.dp)
                ) {
                    Checkbox(checked = isChecked, onCheckedChange = { isChecked = it })
                    Text(
                        color = Red,
                        fontStyle = FontStyle.Italic,
                        fontSize = 14.sp,
                        text = "important task"
                    )
                }
            }
        }
    }
}

