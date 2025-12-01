package com.example.androideksamen.components.shared

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ErrorLoading(errorType: Int, errorItem: String? = null) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(40.dp)
    )
    {
        if (errorType == 1) {
            Text(
                text = "We're currently having an error loading ${errorItem}. \nPlease check your network connection and try again",
                fontSize = 16.sp,
                color = Onyx,
                modifier = Modifier
                    .padding(top = 8.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        } else {
            Text(
                text = "No anime with this ID exists. \nPlease try a different Anime ID",
                fontSize = 16.sp,
                color = Onyx,
                modifier = Modifier
                    .padding(top = 8.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
        Icon(
            imageVector = Icons.Outlined.Info,
            contentDescription = "Error symbol",
            tint = DustyGrape,
            modifier = Modifier
                .size(112.dp)
                .rotate(180f)

        )
    }

}