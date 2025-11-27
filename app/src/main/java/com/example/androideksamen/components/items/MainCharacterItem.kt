package com.example.androideksamen.components.items

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import coil.compose.AsyncImage
import com.example.androideksamen.data.dataclasses.character.Character

//TODO: filter etter fav
//TODO: søk
//TODO: endre fontSize i animeitem så det er likt som characteritem
@Composable
fun MainCharacterItem(
    character: Character
) {

    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Color(0xFFFDF1B2),
                shape = RoundedCornerShape(8.dp)
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(104.dp)
                .background(
                    color = Color(0xFFF7EAF9),
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)

            ) {
                Text(
                    text = character.name,
                    fontSize = if (character.name.length > 16) 16.sp else 24.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 8.dp)
                )


                //LINK TO MYANIMELIST WIKI FOR CHARACTER

                Text(
                    text = "Open in MyAnimeList.net",
                    color = Color.Black,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .clickable {
                            val intent = Intent(Intent.ACTION_VIEW, character.url?.toUri())
                            context.startActivity(intent)
                        }
                )
            }




            Spacer(modifier = Modifier.width(8.dp))

            AsyncImage(
                model = character.characterImage?.jpg?.imageUrl,
                contentDescription = "Bilde av ${character.name}",
                modifier = Modifier
                    .size(80.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainCharacterItemPreview() {
    MainCharacterItem(
        character = Character(
            id = 1,
            name = "Pikachu",
            characterImage = null,
            nameJapanese = "Japansk Pikachu",
            url = "https://www.youtube.com/watch?v=dQw4w9WgXcQ"
        )

    )


}

