package com.example.androideksamen.components.shared

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.androideksamen.fonts.BodyFont

// Setter styling for brødtekst
// Laget for å unngå gjentagende kode
// Brukes i AnimeDetailsItem og AnimeInfo
@Composable
fun BodyText(bodyText: String) {
    Text(
        text = bodyText,
        fontFamily = BodyFont,
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        color = Onyx
    )
}