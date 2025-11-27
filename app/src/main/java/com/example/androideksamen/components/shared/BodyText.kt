package com.example.androideksamen.components.shared

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.androideksamen.fonts.BodyFont

@Composable
fun BodyText(bodyText: String) {
    Text(
        text = bodyText,
        fontFamily = BodyFont,
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        color = Color(0xFF0A0E0D)
    )
}