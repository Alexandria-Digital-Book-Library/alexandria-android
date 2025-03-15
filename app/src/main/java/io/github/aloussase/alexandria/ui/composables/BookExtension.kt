package io.github.aloussase.alexandria.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import io.github.aloussase.alexandria.R
import io.github.aloussase.alexandria.domain.model.Book
import io.github.aloussase.alexandria.ui.viewmodel.DownloadsEvent
import io.github.aloussase.alexandria.ui.viewmodel.DownloadsViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun BookExtension(
    book: Book,
    modifier: Modifier = Modifier,
    viewModel: DownloadsViewModel = koinViewModel()
) {
    ElevatedButton(
        onClick = { viewModel.onEvent(DownloadsEvent.InitiateDownload(book.downloadUrl)) },
        modifier = modifier,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            Icon(
                Icons.Filled.ArrowDropDown,
                contentDescription = stringResource(id = R.string.download_book)
            )
            Box(modifier = Modifier.width(2.dp))
            Text(book.extension.uppercase())
        }
    }
}