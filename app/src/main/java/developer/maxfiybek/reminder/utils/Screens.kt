package developer.maxfiybek.reminder.utils

import kotlinx.serialization.Serializable

object Screens {

    @Serializable
    data object Main

    @Serializable
    data object CreateTask

    @Serializable
    data class C(val name: String?, val lastName: String?)
}