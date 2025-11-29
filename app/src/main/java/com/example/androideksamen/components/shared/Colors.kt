package com.example.androideksamen.components.shared

import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults.colors
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults.colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Hovedfargene vi bruker ofte
val Onyx = Color(0xFF0A0E0D)
val DarkPink = Color(0xFFFBBAED)
val LightPink = Color(0xFFF7EAF9)
val DarkBlue = Color(0xFF324663)
val LightYellow = Color(0xFFfdf1b2)

// Tema for NavBar
@Composable
fun navBarTheme(): NavigationBarItemColors {
    return colors(
        indicatorColor = Color(0xFF0A0E0D),
        selectedIconColor = Color(0xFFF5F5F5),
        unselectedIconColor = Color(0xFFF5F5F5),
        selectedTextColor = Color(0xFFF5F5F5),
        unselectedTextColor = Color(0xFFF5F5F5)
    )
}

// Tema for input
@Composable
fun inputTheme(): TextFieldColors {
    return colors(
        focusedContainerColor = Color.White,
        unfocusedContainerColor = Color(0xFFF7EAF9),
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        focusedPlaceholderColor = Color(0xFF0A0E0D),
        unfocusedPlaceholderColor = Color(0xFF0A0E0D),
        focusedTextColor = Color(0xFF0A0E0D),
        unfocusedTextColor = Color(0xFF0A0E0D)
    )
}

// Tema for knapper
@Composable
fun buttonTheme(): ButtonColors {
    return ButtonDefaults.buttonColors(
        containerColor = DarkBlue,
        contentColor = Color.White
    )
}
