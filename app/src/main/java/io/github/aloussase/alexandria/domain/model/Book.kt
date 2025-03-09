package io.github.aloussase.alexandria.domain.model

data class Book(
    val title: String,
    val authors: List<String>,
    val shouldOpenBrowser: Boolean,
    val extension: String,
    val imageUrl: String,
    val downloadUrl: String,
    val size: String
)
