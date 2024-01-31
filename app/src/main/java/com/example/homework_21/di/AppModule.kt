package com.example.homework_21.di

import android.content.Context
import androidx.room.Room
import com.example.homework_21.BuildConfig
import com.example.homework_21.data.local.dao.item.ItemDao
import com.example.homework_21.data.local.database.AppDatabase
import com.example.homework_21.data.common.ResponseHandler
import com.example.homework_21.data.remote.service.item.ItemApiService
import com.example.homework_21.data.util.NetworkConnectionChecker
import com.example.homework_21.data.util.NetworkConnectionCheckerImpl
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideResponseHandler(): ResponseHandler {
        return ResponseHandler()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().setLevel(
                    HttpLoggingInterceptor.Level.BODY
                )
            )
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofitClient(): Retrofit {
        return Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
                )
            ).client(provideOkHttpClient()).build()
    }

    @Singleton
    @Provides
    fun provideRetrofitService(
        retrofit: Retrofit
    ): ItemApiService {
        return retrofit.create(ItemApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java, "item-database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideItemDao(appDatabase: AppDatabase): ItemDao {
        return appDatabase.itemDao()
    }

    @Provides
    @Singleton
    fun provideNetworkConnectionManager(
        @ApplicationContext context: Context
    ): NetworkConnectionChecker {
        return NetworkConnectionCheckerImpl(context)
    }
}