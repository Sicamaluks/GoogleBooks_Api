package co.za.fat.googlebooks_api.di.module

import android.app.Application
import android.content.Context
import co.za.fat.googlebooks_api.network.api.BooksNetworkService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
object AppModule {
    @Provides
    @Singleton
    fun provideContext(app: Application): Context {
        return app.applicationContext
    }

    // GsonBuilder().create()
    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BooksNetworkService.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun providesBooksNetworkService(retrofit: Retrofit): BooksNetworkService =
        retrofit.create(BooksNetworkService::class.java)
}