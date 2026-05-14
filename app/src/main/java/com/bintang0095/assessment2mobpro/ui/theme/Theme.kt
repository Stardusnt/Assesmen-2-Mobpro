package com.bintang0095.assessment2mobpro.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColors =
    lightColorScheme(

        primary =
            BluePrimary,

        secondary =
            BlueSecondary
    )

private val DarkColors =
    darkColorScheme(

        primary =
            BluePrimary,

        secondary =
            BlueSecondary
    )

@Composable
fun Assessment2MobproTheme(

    darkTheme: Boolean =
        isSystemInDarkTheme(),

    content: @Composable () -> Unit
) {

    val colors =

        if (darkTheme)

            DarkColors

        else

            LightColors

    MaterialTheme(

        colorScheme = colors,

        typography = Typography,

        content = content
    )
}