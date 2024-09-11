package developer.maxfiybek.reminder.navigation

import kotlinx.serialization.Serializable

sealed interface Screens {

    @Serializable
    data object Main : Screens

    @Serializable
    data object CreateTask : Screens

    @Serializable
    data class C(val name: String?, val lastName: String?) : Screens
}