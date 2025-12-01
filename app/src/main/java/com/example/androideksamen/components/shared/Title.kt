package com.example.androideksamen.components.shared

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androideksamen.fonts.TitleFont

// Styling for tittel
// Brukes i alle skjermer med unntak av AnimeDetailsItem
@Composable
fun Title(title: String) {
    Text(
        text = title,
        fontFamily = TitleFont,
        fontSize = 40.sp,
        fontWeight = FontWeight.Bold,
        color = Onyx,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    )
}