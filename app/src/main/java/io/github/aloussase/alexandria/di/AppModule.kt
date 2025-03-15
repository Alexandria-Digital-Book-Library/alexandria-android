package io.github.aloussase.alexandria.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import io.github.aloussase.alexandria.domain.interfaces.AlexandriaAPI
import io.github.aloussase.alexandria.ui.viewmodel.DownloadsViewModel
import io.github.aloussase.alexandria.ui.viewmodel.HomeViewModel
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.lang.ref.WeakReference
import java.util.concurrent.TimeUnit

object AppModule {
    private const val BASE_URL = "https://alexandriapi.frustrated-functor.dev"

    private val moshi = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()

    private val httpClient = OkHttpClient()
        .newBuilder()
        .readTimeout(5, TimeUnit.MINUTES)
        .build()

    val get = module {
        single {
            Retrofit.Builder()
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .client(httpClient)
                .baseUrl(BASE_URL)
                .build()
                .create(AlexandriaAPI::class.java)
        }

        viewModelOf(::HomeViewModel)

        viewModel {
            DownloadsViewModel(WeakReference(androidContext()))
        }
    }
}