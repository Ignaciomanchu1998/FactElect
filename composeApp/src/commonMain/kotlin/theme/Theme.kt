package theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.DpSize


private val DarkColorScheme = darkColorScheme(
    primary = primaryWhite,
    secondary = gray,
    tertiary = lightGray,
    surface = primaryBlack,
    background = primaryBlack,
)

private val DarkColorScheme = ligthColorScheme(
    primary = primaryBlack,
    secondary = gray,
    tertiary = lightGray,
    surface = primaryWhite,
    background = primaryWhite,
)

private val LocalDimens = staticCompositionLocalOf { DefaultDimens }

@Composable
fun ProvidesDimens(dimens: Dimens, content: @Composable () -> Unit) {
    val dimensionsSet = remember { dimens }
    CompositionLocalProvider(LocalDimens provides dimensionsSet, content = content)
}

@Composable
fun FactElectTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            (view.context as Activity).window.statusBarColor = colorScheme.primary.toArgb()
            (view.context as Activity).window.navigationBarColor = colorScheme.primary.toArgb()
        }
    }

    val dimensions = if (windowSize > WindowWidthSizeClass.Compact)
        TabletDimens
    else
        DefaultDimens

    ProvidesDimens(dimens = dimensions) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
        )
    }
}