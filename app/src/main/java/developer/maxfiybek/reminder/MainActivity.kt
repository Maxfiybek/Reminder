package developer.maxfiybek.reminder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import developer.maxfiybek.reminder.components.theme.ReminderTheme
import developer.maxfiybek.reminder.navigation.ReminderNav

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ReminderTheme {
                ReminderNav()
            }
        }
    }
}

