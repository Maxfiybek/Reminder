package developer.maxfiybek.reminder.navigation

import kotlinx.serialization.Serializable

sealed interface Screens {
    @Serializable
    data object Main : Screens

    @Serializable
    data object CreateTask : Screens
}