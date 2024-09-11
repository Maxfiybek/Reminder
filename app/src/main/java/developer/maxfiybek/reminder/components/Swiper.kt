package developer.maxfiybek.reminder.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissState
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.unit.dp
import developer.maxfiybek.reminder.data.db.entity.TaskModelEntity
import developer.maxfiybek.reminder.enums.SwipeType
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> SwipeToDelete(
    item: T,
    onSwipe: (SwipeType, Int) -> Unit,
    animation: Int = 500,
    content: @Composable (T) -> Unit,
) {
    var isEdited by remember { mutableStateOf(false) }
    var isRemoved by remember { mutableStateOf(false) }

    val dismissState = rememberDismissState(
        confirmValueChange = { state ->
            when (state) {
                DismissValue.DismissedToStart -> {
                    onSwipe(SwipeType.FROM_RIGHT_TO_LEFT, (item as TaskModelEntity).id)
                    isRemoved = true
                    false
                }

                DismissValue.DismissedToEnd -> {
                    onSwipe(SwipeType.FROM_LEFT_TO_RIGHT, (item as TaskModelEntity).id)
                    isEdited = true
                    false
                }

                else -> {
                    false
                }
            }
        }
    )

    AnimatedVisibility(
        visible = !isRemoved,
        exit = shrinkVertically(
            animationSpec = tween(
                durationMillis = animation
            ),
            shrinkTowards = Alignment.Top
        ) + fadeOut()
    ) {
        SwipeToDismiss(
            state = dismissState, background = {
                SwiperBackground(swipeDismissState = dismissState)
            },
            dismissContent = { content(item) },
            directions = setOf(DismissDirection.StartToEnd, DismissDirection.EndToStart)
        )
    }

    LaunchedEffect(isRemoved) {
        if (isRemoved) {
            delay(animation.toLong())
            onSwipe(SwipeType.FROM_RIGHT_TO_LEFT, (item as TaskModelEntity).id)
        }
    }

    LaunchedEffect(isEdited) {
        if (isEdited) {
            delay(animation.toLong())
            onSwipe(SwipeType.FROM_LEFT_TO_RIGHT, (item as TaskModelEntity).id)
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SwiperBackground(
    swipeDismissState: DismissState,
    modifier: Modifier = Modifier,
) {
    if (swipeDismissState.dismissDirection == DismissDirection.EndToStart) {
        Box(
            contentAlignment = Alignment.CenterEnd,
            modifier = modifier
                .fillMaxSize()
                .padding(10.dp)
                .clip(RoundedCornerShape(6.dp))
                .background(Red)
                .padding(16.dp)
        ) {

            Icon(
                modifier = modifier,
                imageVector = Icons.Default.Delete,
                contentDescription = "deleter icon"
            )
        }
    } else if (swipeDismissState.dismissDirection == DismissDirection.StartToEnd) {
        Box(
            contentAlignment = Alignment.CenterStart,
            modifier = modifier
                .fillMaxSize()
                .padding(10.dp)
                .clip(RoundedCornerShape(6.dp))
                .background(Green)
                .padding(16.dp)
        ) {
            Icon(
                modifier = modifier,
                imageVector = Icons.Default.Edit,
                contentDescription = "editor icon"
            )
        }
    }
}
