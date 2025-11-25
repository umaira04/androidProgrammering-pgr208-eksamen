package com.example.androideksamen.components.items

import androidx.compose.foundation.background
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.androideksamen.data.dataclasses.character.Character


//TODO: legge til fav
//TODO: filter etter fav
//TODO: søk
//TODO: endre fontSize i animeitem så det er likt som characteritem
@Composable
//hvorfor har du ikke brukt dataklassen i character som også har favorites??
fun CharacterItem(
    character: Character,
    isFavorite: Boolean = false
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(
                color = Color(0xFFFDF1B2),
                shape = RoundedCornerShape(8.dp)
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(104.dp)
                .padding(8.dp),
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
                    fontSize = if (character.name.toString().length > 16) 16.sp else 24.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Text(
                    text = character.nameJapanese ?: "No japanese name",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.Black
                )

                Text(
                    text = "Noe info her også",
                    fontSize = 16.sp,
                    color = Color.Black
                )
            }

            // TODO : legge inn link til myanimelist.com wiki til karakteren?

            IconButton(
                onClick = {},
                modifier = Modifier.size(40.dp)
            ) {
                Icon(
                    imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                    contentDescription = "Favorite",
                    modifier = Modifier.size(40.dp),
                    tint = Color.Red
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
fun CharacterItemPreview() {
    CharacterItem(
        character = Character(
            id = 1,
            name = "Pikachu",
            characterImage = null,
            nameJapanese = "Japansk Pikachu",
        ),
        isFavorite = false
    )


}

