package com.raychal.core.di

import androidx.room.Room
import com.raychal.core.data.MovieAppRepository
import com.raychal.core.data.source.local.LocalDataSource
import com.raychal.core.data.source.local.room.CatalogDatabase
import com.raychal.core.data.source.remote.RemoteDataSource
import com.raychal.core.data.source.remote.network.ApiService
import com.raychal.core.domain.repositoriy.IMovieAppRepository
import com.raychal.core.utils.AppExecutors
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val databaseModule = module {
    factory { get<CatalogDatabase>().catalogDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            CatalogDatabase::class.java, "movie.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IMovieAppRepository> { MovieAppRepository(get(), get(), get()) }
}