package com.example.githubclientwithkotlin.API

import com.example.githubclientwithkotlin.Entity.Repository
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class APIClient {

    lateinit var retrofit: Retrofit

    private val apiClientBuilder: OkHttpClient.Builder get() {
        val httpclient = OkHttpClient.Builder()
            .addInterceptor(Interceptor { chain ->
                val original = chain.request()

                val request = original.newBuilder()
                    .header("Accept", "application")
                    .method(original.method(), original.body())
                    .build()
                return@Interceptor chain.proceed(request)
            })
            .readTimeout(30, TimeUnit.SECONDS)

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        httpclient.addInterceptor(loggingInterceptor)

        return httpclient
    }

    fun fetchRepository() : Call<Repository> {
        val service = create(GithubService::class.java)
        return service.fetchRepository()
    }

    private fun <S> create(serviceClass: Class<S>): S {
        val gson = GsonBuilder()
            .serializeNulls()
            .create()

        // create retrofit
        retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl("https://api.github.com/") // Put your base URL
            .client(apiClientBuilder.build())
            .build()

        return retrofit.create(serviceClass)
    }
}

