package io.github.aloussase.alexandria.ui.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import io.github.aloussase.alexandria.domain.model.Book

@Composable
fun BookCard(
    book: Book,
    modifier: Modifier = Modifier
) {
    ElevatedCard(
        modifier = modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        Row {
            AsyncImage(
                model = ImageRequest
                    .Builder(LocalContext.current)
                    .data(book.imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = book.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier.clip(RectangleShape)
            )
            Box(modifier = Modifier.width(2.dp))
            Column(
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxHeight()
            ) {
                Text(book.title.uppercase())
                Text(
                    text = book.authors.joinToString(separator = ", "),
                    fontSize = 10.sp,
                )
                Text(
                    text = book.size,
                    fontSize = 8.sp,
                )
                Box(
                    modifier = Modifier
                        .fillMaxHeight(1f)
                )
                BookExtension(book.extension)
            }
        }
    }
}