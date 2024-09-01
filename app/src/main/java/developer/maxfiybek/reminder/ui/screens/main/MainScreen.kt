package developer.maxfiybek.reminder.ui.screens.main

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import developer.maxfiybek.reminder.R
import developer.maxfiybek.reminder.components.TodoFloatingActionButton
import developer.maxfiybek.reminder.components.TopBar
import developer.maxfiybek.reminder.components.theme.Primary70
import developer.maxfiybek.reminder.components.theme.TaskTextColor
import developer.maxfiybek.reminder.components.theme.WeakPrimary
import developer.maxfiybek.reminder.utils.Screens

@SuppressLint("ModifierParameter", "UnusedMaterial3ScaffoldPaddingParameter", "Range")
@Composable
fun MainScreenUi(
    context: Context,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val vm = hiltViewModel<MainScreenViewModel>()
    val tasksFromDb = vm.dataList.collectAsState(initial = emptyList()).value
    Scaffold(
        topBar = {
            TopBar(
                title = { Text(text = "Tasks") },
            )
        },
        floatingActionButton = {
            TodoFloatingActionButton(
                onClick = { navController.navigate(Screens.CreateTask) },
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
            LazyColumn(
                userScrollEnabled = true,
                modifier = modifier
                    .fillMaxSize()
                    .padding(top = 4.dp),
            ) {
                items(tasksFromDb.size) {
                    val model = tasksFromDb[it]
                    Column(
                        modifier = modifier
                            .fillMaxWidth()
                            .height(100.dp)
                            .fillMaxHeight(fraction = 100F)
                            .padding(10.dp)
                            .clip(shape = RoundedCornerShape(10.dp))
                            .background(WeakPrimary)
                            .fillMaxWidth()
                            .verticalScroll(rememberScrollState())
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = modifier
                                .padding(10.dp)
                                .fillMaxWidth()
                        ) {
                            Text(
                                modifier = modifier
                                    .padding(start = 10.dp),
                                style = TextStyle(
                                    fontWeight = FontWeight.Bold
                                ),
                                fontSize = 16.sp,
                                color = TaskTextColor,
                                text = model.tasksToRemind,
                                maxLines = 5
                            )
                            if (model.isImportant) {
                                Icon(
                                    tint = Red,
                                    modifier = modifier
                                        .padding(end = 4.dp)
                                        .size(28.dp),
                                    painter = painterResource(id = R.drawable.ic_important),
                                    contentDescription = "important icon"
                                )
                            }
                        }
                        Text(
                            modifier = modifier
                                .padding(start = 20.dp)
                                .fillMaxWidth()
                                .align(Alignment.Start),
                            fontSize = 12.sp,
                            color = TaskTextColor,
                            text = model.dateAndTime
                        )
                    }
                }
            }
            var isNavigating by remember { mutableStateOf(false) }
            Box(
                contentAlignment = Alignment.Center,
                modifier = modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .clickable(
                        enabled = !isNavigating
                    ) {
                        isNavigating = true
                        navController.navigate(Screens.CreateTask)
                    }
                    .background(Primary70),
            ) {
                Icon(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(8.dp),
                    tint = White,
                    painter = painterResource(id = R.drawable.ic_add),
                    contentDescription = "add icon"
                )
            }
        }
    }
}