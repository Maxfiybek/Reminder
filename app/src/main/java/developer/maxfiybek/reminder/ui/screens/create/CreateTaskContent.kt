package developer.maxfiybek.reminder.ui.screens.create

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import developer.maxfiybek.reminder.components.theme.Primary70
import developer.maxfiybek.reminder.components.theme.Purple80
import developer.maxfiybek.reminder.ui.screens.create.intent.CreateTaskState

@Composable
fun CreateTaskContent(
    paddingValues: PaddingValues,
    createTaskState: CreateTaskState,
    onTaskValueChange: (String) -> Unit,
    onCheckedChange: (Boolean) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
    ) {
        TextField(
            value = createTaskState.tasksToRemind,
            onValueChange = onTaskValueChange,
            label = { Text(text = "To do") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 10.dp)
                .background(color = Purple80, shape = RoundedCornerShape(10.dp)),
            singleLine = true,
            colors = TextFieldDefaults.colors(
                unfocusedIndicatorColor = Color.Transparent,
                focusedTextColor = Primary70,
                unfocusedTextColor = Primary70,
                cursorColor = Primary70,
            )
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxWidth()
                .height(30.dp)
        ) {
            Checkbox(checked = createTaskState.isImportant, onCheckedChange = onCheckedChange)
            Text(
                color = Red,
                fontStyle = FontStyle.Italic,
                fontSize = 14.sp,
                text = "important task"
            )
        }
    }
}