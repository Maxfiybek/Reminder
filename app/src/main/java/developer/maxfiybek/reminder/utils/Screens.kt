package developer.maxfiybek.reminder.utils

import kotlinx.serialization.Serializable

object Screens {

    @Serializable
    object Main

    @Serializable
    object CreateTask

    @Serializable
    data class C(val name: String?, val lastName: String?)
}