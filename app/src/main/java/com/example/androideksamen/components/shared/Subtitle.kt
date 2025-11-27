package com.example.androideksamen.components.shared

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.androideksamen.fonts.BodyFont

@Composable
fun Subtitle(subtitle: String) {
    Text(
        text = subtitle,
        fontFamily = BodyFont,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        color = Onyx
    )
}