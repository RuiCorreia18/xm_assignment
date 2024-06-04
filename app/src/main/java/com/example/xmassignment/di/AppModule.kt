package com.example.xmassignment.di

import com.example.xmassignment.data.QuestionApi
import com.example.xmassignment.data.QuestionRepositoryImpl
import com.example.xmassignment.domain.QuestionRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): QuestionApi {
        return Retrofit.Builder()
            .baseUrl("https://xm-assignment.web.app/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
            .create(QuestionApi::class.java)
    }
}

@Module
abstract class AppBindModule {
    @Binds
    abstract fun bindRepository(repositoryImpl: QuestionRepositoryImpl): QuestionRepository
}