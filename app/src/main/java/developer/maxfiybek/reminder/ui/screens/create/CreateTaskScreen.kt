package developer.maxfiybek.reminder.ui.screens.create

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import developer.maxfiybek.reminder.R
import developer.maxfiybek.reminder.ui.theme.Primary70
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun CreateTaskScreen(navController: NavHostController, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.Start,
    ) {
        CreateTaskToolbar(navController = navController)
        FirstTextField()
        TasKCheckBox()
    }
}

@Composable
private fun CreateTaskToolbar(navController: NavHostController, modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(Primary70)
    ) {
        Icon(
            modifier = modifier
                .clickable {
                    navController.popBackStack()
                }
                .fillMaxHeight()
                .padding(start = 10.dp, end = 14.dp)
                .size(26.dp),
            painter = painterResource(id = R.drawable.ic_back_simple),
            tint = Color.White,
            contentDescription = "ic_back"
        )
        Text(
            color = Color.White,
            modifier = modifier,
            fontSize = 18.sp,
            text = "New Task"
        )
    }
}

@Composable
private fun FirstTextField(modifier: Modifier = Modifier) {
    var text by remember { mutableStateOf("") }
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
}

@Composable
private fun TasKCheckBox(modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth()
    ) {
        var isChecked by remember { mutableStateOf(false) }
        Checkbox(
            checked = isChecked,
            onCheckedChange = { isChecked = it }
        )
        Text(
            fontSize = 14.sp,
            style = TextStyle(
                fontStyle = FontStyle.Italic
            ),
            color = Color.Red,
            text = "important task"
        )

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ForPreview(modifier: Modifier = Modifier) {
    CreateTaskScreen(navController = rememberNavController())
}


@SuppressLint("NewApi")
private fun getCurrentTime(): String {
    val current = LocalDateTime.now()
    val formatter = DateTimeFormatter.ofPattern("dd:mm:yyyy")
    return current.format(formatter)
}