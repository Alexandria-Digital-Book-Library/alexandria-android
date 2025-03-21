package io.github.aloussase.alexandria.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.aloussase.alexandria.domain.interfaces.AlexandriaAPI
import io.github.aloussase.alexandria.domain.model.Book
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class HomeState(
    val query: String = "",
    val books: List<Book> = emptyList()
)

sealed class HomeEvent {
    data class SetSearchQuery(val query: String) : HomeEvent()
    data object SearchBooks : HomeEvent()
}

class HomeViewModel(
    val api: AlexandriaAPI
) : ViewModel() {

    companion object {
        private const val TAG = "HomeViewModel";
    }

    private val _state: MutableStateFlow<HomeState> = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    fun on(evt: HomeEvent) {
        when (evt) {
            is HomeEvent.SearchBooks -> onSearchBooks()
            is HomeEvent.SetSearchQuery -> onSetSearchQuery(evt)
        }
    }

    private fun onSetSearchQuery(evt: HomeEvent.SetSearchQuery) {
        Log.d(TAG, "Setting search query to ${evt.query}")
        _state.update {
            it.copy(
                query = evt.query
            )
        }
    }

    private fun onSearchBooks() {
        val query = _state.value.query
        Log.d(TAG, "Searching for books with query: $query")

        if (query.isEmpty()) {
            return
        }

        viewModelScope.launch {
            val books = api.searchBooks(query)
            _state.update {
                it.copy(
                    books = books
                )
            }
        }
    }
}