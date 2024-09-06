package developer.maxfiybek.reminder.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import developer.maxfiybek.reminder.ui.screens.create.CreateTaskScreen
import developer.maxfiybek.reminder.ui.screens.main.MainScreenUi
import developer.maxfiybek.reminder.utils.Screens

@Composable
fun ReminderNav() {
    val navController = rememberNavController()
    val context = LocalContext.current
    NavHost(navController = navController, startDestination = Screens.Main) {
        composable<Screens.Main> {
            MainScreenUi(navController = navController)
        }
        composable<Screens.CreateTask> {
            CreateTaskScreen(navController = navController)
        }
    }
}