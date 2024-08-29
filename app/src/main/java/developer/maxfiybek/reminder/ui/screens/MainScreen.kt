package developer.maxfiybek.reminder.ui.screens

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
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
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import developer.maxfiybek.reminder.R
import developer.maxfiybek.reminder.data.db.entity.TaskModelEntity
import developer.maxfiybek.reminder.ui.theme.ColorPrimary
import developer.maxfiybek.reminder.ui.theme.Primary70
import developer.maxfiybek.reminder.ui.theme.TaskTextColor
import developer.maxfiybek.reminder.ui.theme.WeakPrimary
import developer.maxfiybek.reminder.ui.vm.MainScreenViewModel
import developer.maxfiybek.reminder.utils.Screens
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@SuppressLint("ModifierParameter", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreenUi(
    context: Context,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val vm = hiltViewModel<MainScreenViewModel>()
    MainScreenToolbar(navController = navController, vm = vm, context = context)
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenToolbar(
    navController: NavHostController,
    vm: MainScreenViewModel,
    context: Context,
    modifier: Modifier = Modifier
) {
    val menuItems = listOf(
        "Work 1",
        "Work 2",
        "Work 3"
    )
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = ColorPrimary,
                    titleContentColor = White,
                ),
                title = {
                    Text(
                        "Reminder",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                actions = {
                    var isExpanded by remember {
                        mutableStateOf(false)
                    }
                    IconButton(onClick = { isExpanded = true }) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = "Menu Icon",
                            tint = White
                        )
                    }
                    DropdownMenu(
                        expanded = isExpanded,
                        onDismissRequest = { isExpanded = false }
                    ) {
                        menuItems.forEach {
                            DropdownMenuItem(text = { Text(text = it) },
                                onClick = {
                                    isExpanded = false
                                    Toast.makeText(
                                        context,
                                        "Clicked $it",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            )
                        }
                    }
                },
            )
        },
    ) { _ ->
        val list = vm.dataLis.collectAsState(initial = emptyList()).value
        TasksList(navController = navController, vm, tasksItems = list)
        Box(
            modifier = modifier
                .fillMaxSize()
        ) {
            Row(
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.Bottom,
                modifier = modifier
                    .fillMaxSize()
                    .padding(end = 16.dp, bottom = 30.dp)
            ) {
                CreateTaskButton(navController = navController)
            }
        }
    }
}

@Composable
fun TasksList(
    navController: NavHostController,
    viewModel: MainScreenViewModel,
    tasksItems: List<TaskModelEntity>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 20.dp)
    ) {
        LazyColumn(
            userScrollEnabled = true,
            modifier = modifier
                .fillMaxSize()
                .padding(top = 4.dp),
        ) {
            items(tasksItems.size) {
                ShowItemsInRecycle(model = tasksItems[it])
            }
        }
    }
}

@SuppressLint("Range")
@Composable
fun ShowItemsInRecycle(
    model: TaskModelEntity,
    modifier: Modifier = Modifier
) {
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


//@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainScreenUiPreview() {
}

@Composable
fun CreateTaskButton(navController: NavHostController, modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .size(60.dp)
            .clip(RoundedCornerShape(12.dp))
            .clickable {
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