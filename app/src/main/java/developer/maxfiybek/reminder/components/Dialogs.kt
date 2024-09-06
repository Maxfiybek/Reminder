package developer.maxfiybek.reminder.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import developer.maxfiybek.reminder.components.theme.Primary70
import developer.maxfiybek.reminder.components.theme.WeakPrimary
import developer.maxfiybek.reminder.data.db.entity.TaskModelEntity
import developer.maxfiybek.reminder.utils.getCurrentTime

@Composable
fun EditorDialog(
    modifier: Modifier = Modifier,
    model: TaskModelEntity,
    onDismiss: () -> Unit,
    onConfirm: (editedModel: TaskModelEntity) -> Unit,
) {
    var updatedTask by remember { mutableStateOf(model.tasksToRemind) }
    var updatedIsImportant by remember { mutableStateOf(model.isImportant) }
    val editedModel = TaskModelEntity(
        id = model.id,
        tasksToRemind = updatedTask,
        isImportant = updatedIsImportant,
        dateAndTime = getCurrentTime()
    )
    Dialog(
        onDismissRequest = { onDismiss() },
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        )
    ) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(400.dp)
                .padding(20.dp)
                .background(color = WeakPrimary, shape = RoundedCornerShape(10.dp))
        ) {
            Column(
                modifier = modifier
                    .padding(top = 30.dp, start = 10.dp, end = 10.dp)
                    .fillMaxSize()
            ) {
                TextField(
                    value = updatedTask,
                    onValueChange = { updatedTask = it },
                    singleLine = true,
                    shape = RoundedCornerShape(10.dp),
                    modifier = modifier
                        .border(width = 0.dp, color = Color.Transparent)
                        .fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = Primary70,
                        unfocusedTextColor = Primary70,
                        cursorColor = Primary70,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = modifier
                        .padding(start = 10.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        style = TextStyle(
                            fontWeight = FontWeight.SemiBold,
                        ),
                        color = Color.Red,
                        fontSize = 14.sp,
                        text = "important task",
                    )
                    Checkbox(
                        checked = updatedIsImportant,
                        onCheckedChange = { updatedIsImportant = it }
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.Bottom,
                    modifier = modifier
                        .fillMaxSize()
                ) {
                    Button(
                        modifier = modifier
                            .padding(bottom = 10.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Primary70
                        ),
                        shape = RoundedCornerShape(10.dp),
                        onClick = { onConfirm(editedModel) }
                    ) {
                        Text(text = "Update task")
                    }
                }
            }
        }
    }
}