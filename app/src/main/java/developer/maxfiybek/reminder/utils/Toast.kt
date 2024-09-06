package developer.maxfiybek.reminder.utils

import android.content.Context
import android.widget.Toast

fun Context.makeToast(
    text: String,
    isShort: Boolean = false
) {
    Toast.makeText(this, text, if (isShort) Toast.LENGTH_SHORT else Toast.LENGTH_LONG).show()
}