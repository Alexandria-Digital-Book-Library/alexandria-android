package io.github.aloussase.alexandria.domain.interfaces

import io.github.aloussase.alexandria.domain.model.Book
import retrofit2.http.GET
import retrofit2.http.Query

interface AlexandriaAPI {
    @GET("api/books")
    fun searchBooks(@Query("title") title: String): List<Book>
}