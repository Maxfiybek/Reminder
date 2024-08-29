package developer.maxfiybek.reminder.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import developer.maxfiybek.reminder.utils.Screens

@Composable
fun SearchScreenUi(
    model: Screens.C?,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Hello from ${model?.name} ${model?.lastName}")
        Button(onClick = {
            navController.navigate(Screens.Main)
        }) {
            Text(text = "Go to Create Main Scree page!")
        }
    }
}