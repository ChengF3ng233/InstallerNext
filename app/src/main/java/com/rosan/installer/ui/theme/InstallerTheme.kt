package com.rosan.installer.ui.theme

import android.app.Activity
import android.os.Build
import android.view.WindowManager
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import com.rosan.installer.R

@Composable
fun InstallerTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> darkColorScheme(
            primary = colorResource(R.color.dark_primary),
            primaryContainer = colorResource(R.color.dark_primary_container),
            secondary = colorResource(R.color.dark_secondary),
            tertiary = colorResource(R.color.dark_tertiary),
            error = colorResource(R.color.dark_error)
        )

        else -> lightColorScheme(
            primary = colorResource(R.color.light_primary),
            primaryContainer = colorResource(R.color.light_primary_container),
            secondary = colorResource(R.color.light_secondary),
            tertiary = colorResource(R.color.light_tertiary)
        )
    }
    val view = LocalView.current
    SideEffect {
        val window = (view.context as Activity).window

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P)
            window.attributes.layoutInDisplayCutoutMode =
                WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES

        WindowCompat.setDecorFitsSystemWindows(window, false)

        window.statusBarColor = Color.Transparent.toArgb()
        WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars =
            !darkTheme

        window.navigationBarColor = Color.Transparent.toArgb()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P)
            window.navigationBarDividerColor = Color.Transparent.toArgb()

        WindowCompat.getInsetsController(window, view).isAppearanceLightNavigationBars =
            !darkTheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}