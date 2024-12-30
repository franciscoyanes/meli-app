package com.fran.meliapp.di

import com.fran.meliapp.common.Constants
import com.fran.meliapp.data.remote.MeliApi
import com.fran.meliapp.data.repository.ProductRepositoryImpl
import com.fran.meliapp.domain.repository.ProductRepository
import com.fran.meliapp.domain.use_case.SearchProductUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMeliApi(): MeliApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MeliApi::class.java)
    }

    @Provides
    @Singleton
    fun provideProductRepository(api: MeliApi): ProductRepository {
        return ProductRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideSearchProductUseCase(repository: ProductRepository): SearchProductUseCase {
        return SearchProductUseCase(repository)
    }
}
