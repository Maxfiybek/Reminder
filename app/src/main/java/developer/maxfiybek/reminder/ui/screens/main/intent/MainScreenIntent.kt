package developer.maxfiybek.reminder.ui.screens.main.intent

import android.content.Context
import developer.maxfiybek.reminder.data.db.entity.TaskModelEntity
import developer.maxfiybek.reminder.enums.MenuType
import developer.maxfiybek.reminder.enums.SwipeType

sealed interface MainScreenIntent {

    data object OnMenuShow : MainScreenIntent

    data class OnMenuItemClicked(val menuType: MenuType, val context: Context) : MainScreenIntent

    data object OnFloatingButtonClicked : MainScreenIntent

    data class OnSwiped(val swipeType: SwipeType, val id: Int) : MainScreenIntent

    data object OnDialogDismissed : MainScreenIntent

    data class OnDialogConfirmedToEdit(val model: TaskModelEntity) : MainScreenIntent
}