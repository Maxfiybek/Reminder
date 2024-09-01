package developer.maxfiybek.reminder.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import developer.maxfiybek.reminder.ui.screens.create.CreateTaskScreen
import developer.maxfiybek.reminder.ui.screens.main.MainScreenUi
import developer.maxfiybek.reminder.utils.Screens

@Composable
fun ReminderNav(context: Context) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.Main) {
        composable<Screens.Main> {
            MainScreenUi(context = context, navController = navController)
        }
        composable<Screens.CreateTask> {
            CreateTaskScreen(context = context, navController = navController)
        }
    }
}