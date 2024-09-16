package developer.maxfiybek.reminder.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import developer.maxfiybek.reminder.ui.screens.create.action_intent_event_state.CreateTaskScreen
import developer.maxfiybek.reminder.ui.screens.main.MainScreenUi

@Composable
fun ReminderNav() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.Main) {
        composable<Screens.Main> {
            MainScreenUi(navController = navController)
        }
        composable<Screens.CreateTask> {
            CreateTaskScreen(navController = navController)
        }
    }
}