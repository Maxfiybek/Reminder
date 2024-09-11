package developer.maxfiybek.reminder.common

import developer.maxfiybek.reminder.utils.Screens

sealed interface UiEvent {
    data class Navigation(val screens: Screens) : UiEvent
}