package io.github.aloussase.alexandria.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.aloussase.alexandria.R
import io.github.aloussase.alexandria.ui.viewmodel.HomeEvent
import io.github.aloussase.alexandria.ui.viewmodel.HomeViewModel
import io.github.aloussase.alexandria.ui.composables.BookCard
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel<HomeViewModel>(),
    modifier: Modifier = Modifier
) {
    val state by viewModel.state.collectAsState()

    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = state.query,
                onValueChange = { viewModel.on(HomeEvent.SetSearchQuery(it)) },
                label = { Text(stringResource(R.string.search)) },
                modifier = Modifier.weight(1f)
            )
            TextButton(onClick = { viewModel.on(HomeEvent.SearchBooks) }) {
                Text(stringResource(R.string.do_search))
            }
        }

        if (state.books.isEmpty()) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(stringResource(R.string.such_empty))
                Text(
                    stringResource(R.string.start_searching),
                    fontSize = 12.sp
                )
            }
        }

        if (state.books.isNotEmpty()) {
            Box(modifier = modifier.height(1.dp))
            LazyColumn {
                items(state.books) {
                    BookCard(it)
                }
            }
        }
    }
}