package io.github.aloussase.alexandria.di

import io.github.aloussase.alexandria.domain.interfaces.AlexandriaAPI
import io.github.aloussase.alexandria.ui.HomeViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object AppModule {
    private const val BASE_URL = "https://alexandria.frustrated-functor.dev"

    val get = module {
        single {
            Retrofit.Builder()
                .addConverterFactory(MoshiConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
                .create(AlexandriaAPI::class.java)
        }

        viewModelOf(::HomeViewModel)
    }
}