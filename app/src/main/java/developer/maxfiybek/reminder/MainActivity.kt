package developer.maxfiybek.reminder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import dagger.hilt.android.AndroidEntryPoint
import developer.maxfiybek.reminder.navigation.ReminderNav
import developer.maxfiybek.reminder.ui.theme.ReminderTheme
import developer.maxfiybek.reminder.ui.vm.MainScreenViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ReminderTheme {
                ReminderNav(this)
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun ForPreview(modifier: Modifier = Modifier) {

}

