package com.vibuy.legacy.remote

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by F.K. on 2019-05-02
 *
 */

/**
 * Provide "get" methods to create instances of [BigBurgerService]
 * and its related dependencies, such as OkHttpClient, Gson, etc.
 */

object ServiceFactory {
    private const val BASE_URL = "http://legacy.vibuy.com/dump/"

    fun getBBService(isDebug: Boolean) : BigBurgerService {
        val okHttpClient = getOkHttpClient(
            getLoggingIntercepter(isDebug)
        )

        return getBBService(okHttpClient, getGson())
    }

    private fun getBBService(okHttpClient: OkHttpClient, gson: Gson): BigBurgerService {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        return retrofit.create(BigBurgerService::class.java)
    }

    private fun getOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    private fun getGson(): Gson {
        return GsonBuilder()
            .setLenient()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            .create()
    }

    private fun getLoggingIntercepter(isDebug: Boolean): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()

        logging.level = if (isDebug)
            HttpLoggingInterceptor.Level.BODY
        else
            HttpLoggingInterceptor.Level.NONE

        return logging
    }
}