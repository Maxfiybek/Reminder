package developer.maxfiybek.reminder.ui.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import developer.maxfiybek.reminder.R
import developer.maxfiybek.reminder.components.SwipeToDelete
import developer.maxfiybek.reminder.components.theme.Primary70
import developer.maxfiybek.reminder.components.theme.TaskTextColor
import developer.maxfiybek.reminder.components.theme.WeakPrimary
import developer.maxfiybek.reminder.data.db.entity.TaskModelEntity
import developer.maxfiybek.reminder.enums.MenuType
import developer.maxfiybek.reminder.enums.SwipeType

@Composable
fun MainScreenContent(
    tasksFromDb: List<TaskModelEntity>,
    onSwipe: (SwipeType, Int) -> Unit,
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(paddingValues = paddingValues)
    ) {
        LazyColumn(
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
                    onSwipe = onSwipe
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
    }
}

@Composable
fun MainScreenMenu(
    onShowOption: () -> Unit,
    isShowMenu: Boolean = false,
    onItemClicked: (MenuType) -> Unit = {},
) {
    IconButton(onClick = onShowOption) {
        Icon(Icons.Default.MoreVert, contentDescription = "Menu")
    }
    DropdownMenu(
        expanded = isShowMenu,
        onDismissRequest = { onShowOption() }
    ) {
        DropdownMenuItem(
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_kotlin),
                    contentDescription = "kotlin icon"
                )
            },
            onClick = {
                onItemClicked(MenuType.SOURCE_CODE)
            },
            text = { Text("  Source code", fontSize = 14.sp) }
        )
        DropdownMenuItem(
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_dev),
                    contentDescription = "dev icon"
                )
            },
            onClick = { onItemClicked(MenuType.CREATOR) },
            text = { Text("  Creator", fontSize = 14.sp) }
        )
    }
}