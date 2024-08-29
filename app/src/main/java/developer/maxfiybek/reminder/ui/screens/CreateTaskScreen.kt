package developer.maxfiybek.reminder.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun CreateTaskScreen(navController: NavHostController, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

    }
}

@SuppressLint("NewApi")
fun getCurrentTime(): String {
    val current = LocalDateTime.now()
    val formatter = DateTimeFormatter.ofPattern("dd:mm:yyyy")
    return current.format(formatter)
}