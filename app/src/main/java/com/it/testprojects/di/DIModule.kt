package com.it.testprojects.di

import com.it.testprojects.notesapp.api.UserAPI
import com.it.testprojects.retrofitpaging.api.QuoteAPI
import com.it.testprojects.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DIModule {

    @Singleton
    @Provides
    fun getRetrofit() : Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun getQuoteAPI(retrofit: Retrofit): QuoteAPI {
        return retrofit.create(QuoteAPI::class.java)
    }

    @Singleton
    @Provides
    fun getUserAPI(retrofit: Retrofit): UserAPI {
        return retrofit.create(UserAPI::class.java)
    }


    @Provides
    @Named("sql")
    fun providesUserRepository(sqlRepository: SQLRepository) :UserRepository{
        return sqlRepository
    }

    @Provides
    @FirebaseQualifier
    fun providesFirebaseRepository() :UserRepository{
        return FirebaseRepository()
    }




}