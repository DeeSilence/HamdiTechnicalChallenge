package com.shakatreh.hamditechnicalchallenge.di

import android.content.Context
import androidx.room.Room
import com.shakatreh.hamditechnicalchallenge.R
import com.shakatreh.hamditechnicalchallenge.db.AppDatabase
import com.shakatreh.hamditechnicalchallenge.di.common.CommonRepository
import com.shakatreh.hamditechnicalchallenge.di.common.CommonRepositoryImpl
import com.shakatreh.hamditechnicalchallenge.network.PixabayApis
import com.shakatreh.hamditechnicalchallenge.repos.PixabayRepo
import com.shakatreh.hamditechnicalchallenge.repos.UsersRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideCommonRepository(@ApplicationContext appContext: Context): CommonRepository =
        CommonRepositoryImpl(appContext)

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "Users",
        ).build()
    }

    @Provides
    @Singleton
    fun provideUserRepo(appDatabase: AppDatabase): UsersRepo {
        return UsersRepo(appDatabase)
    }

    @Provides
    @Singleton
    fun providePixabayRepo(pixabayApis: PixabayApis): PixabayRepo {
        return PixabayRepo(pixabayApis)
    }

    @Provides
    @Singleton
    fun providePixabayApis(@ApplicationContext appContext: Context): PixabayApis {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor { chain ->
            val original = chain.request()
            val originalHttpUrl: HttpUrl = original.url()
            val url = originalHttpUrl.newBuilder()
                .addQueryParameter("key", appContext.getString(R.string.api_key))
                .build()
            val requestBuilder = original.newBuilder()
                .url(url)
            val request = requestBuilder.build()
            chain.proceed(request)
        }
        val baseUrl = "https://pixabay.com/"
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()
            .create(PixabayApis::class.java)
    }
}