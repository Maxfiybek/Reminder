package developer.maxfiybek.reminder.components

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import developer.maxfiybek.reminder.components.theme.Primary70
import developer.maxfiybek.reminder.data.db.entity.TaskModelEntity

@Composable
fun TodoFloatingActionButton(
    item: TaskModelEntity? = null,
    onClick: () -> Unit,
    @DrawableRes iconFromDrawable: Int? = null,
    icon: ImageVector? = null,
    contentDescription: String
) {
    FloatingActionButton(
        onClick = onClick,
        containerColor = Primary70,
        contentColor = Color.White,
    ) {
        if (icon != null && iconFromDrawable == null) {
            Icon(
                imageVector = icon,
                contentDescription = contentDescription,
            )
        } else if (icon == null && iconFromDrawable != null) {
            Icon(
                painter = painterResource(id = iconFromDrawable),
                contentDescription = contentDescription,
            )
        } else {
            throw RuntimeException("You can only use one option to set icon")
        }
    }
}

@SuppressLint("ModifierParameter")
@Composable
fun TopBarIconButton(
    onClick: () -> Unit,
    @DrawableRes imageVectorFromDrawable: Int? = null,
    icon: ImageVector? = null,
    contentDescription: String,
    modifier: Modifier = Modifier,
    tint: Color = LocalContentColor.current
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        if (icon != null && imageVectorFromDrawable == null) {
            Icon(
                imageVector = icon,
                contentDescription = contentDescription,
                tint = tint
            )
        } else if (icon == null && imageVectorFromDrawable != null) {
            Icon(
                painter = painterResource(id = imageVectorFromDrawable),
                contentDescription = contentDescription,
                tint = tint
            )
        } else {
            throw RuntimeException("You can only use one option to set icon")
        }
    }
}