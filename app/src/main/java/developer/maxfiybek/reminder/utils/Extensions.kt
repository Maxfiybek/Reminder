package developer.maxfiybek.reminder.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun getCurrentTime(): String {
    val currentTime = Date()
    val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    val formattedDate = formatter.format(currentTime)
    return formattedDate
}