package com.example.androideksamen.components.shared

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun Subtitle(subtitle: String){
    Text(
        text = subtitle,
        color = Color(0xFF0A0E0D),
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold
    )
}