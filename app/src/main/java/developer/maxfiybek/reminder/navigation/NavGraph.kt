package developer.maxfiybek.reminder.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import developer.maxfiybek.reminder.ui.screens.CreateTaskScreen
import developer.maxfiybek.reminder.ui.screens.MainScreenUi
import developer.maxfiybek.reminder.ui.screens.SearchScreenUi
import developer.maxfiybek.reminder.utils.Screens

@Composable
fun ReminderNav(context: Context) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.Main) {
        composable<Screens.Main> {
            MainScreenUi(context = context, navController)
        }
        composable<Screens.CreateTask> {
            CreateTaskScreen(navController)
        }
        composable<Screens.C> {
            SearchScreenUi(it.toRoute<Screens.C>(), navController)
        }
    }
}