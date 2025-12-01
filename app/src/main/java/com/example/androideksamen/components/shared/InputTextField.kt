package com.example.androideksamen.components.shared

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

// Inputfelt for tekst som brukes i AnimeIdeasScreen
// Sikrer lik styling av alle tekst-inputfelt i appen
// Ønsket i utgangspunktet å lage en felles komponent som kunne brukes for input av både tekst og tall,
// men endte med å droppe dette da det ble litt for komplekst basert på nåværende kunnskap

@Composable
fun InputTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(placeholder) },
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .clip(RoundedCornerShape(32.dp))
            .fillMaxWidth(),
        colors = inputTheme()
    )
}