package ru.inc.simplecoinmarketcap.di.module

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.inc.simplecoinmarketcap.model.data.api.ApiDataSource
import ru.inc.simplecoinmarketcap.model.data.api.RemoteDataSourceImpl
import ru.inc.simplecoinmarketcap.model.repositories.RemoteDataSource
import javax.inject.Singleton

@Module
class ApiModule {

    @Provides
    @Singleton
    fun remoteDataSourceProvide(api: ApiDataSource): RemoteDataSource = RemoteDataSourceImpl(api)

    @Provides
    @Singleton
    fun apiProvide(client: OkHttpClient, gson: Gson): ApiDataSource = Retrofit.Builder()
        .baseUrl("https://min-api.cryptocompare.com")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()
        .create(ApiDataSource::class.java)

    @Provides
    @Singleton
    fun gsonProvide(): Gson = GsonBuilder()
//        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .excludeFieldsWithoutExposeAnnotation()
        .create()

    @Provides
    @Singleton
    fun clientProvide(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()
}