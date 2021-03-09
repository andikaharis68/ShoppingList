package com.example.shoppinglist.di

import android.content.Context
import android.content.SharedPreferences
import com.example.shoppinglist.MainActivity
import com.example.shoppinglist.api.AuthTokenInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AplicationModule {
    @Singleton
    @Provides
    fun provideOkHttp3(authTokenInterceptor: AuthTokenInterceptor): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(authTokenInterceptor).build()
    }

    @Singleton
    @Provides
    fun provideMoshiConverter(): MoshiConverterFactory {
        return MoshiConverterFactory.create()
    }

    @Singleton
    @Provides
    fun provideRetrofitInstance(
            okHttpClient: OkHttpClient,
            moshi: MoshiConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
                .baseUrl(MainActivity.baseUrl)
                .client(okHttpClient)
                .addConverterFactory(moshi)
                .build()
    }

    @Singleton
    @Provides
    fun provideSharePref(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("CalculatorSharedPref", Context.MODE_PRIVATE)
    }

}