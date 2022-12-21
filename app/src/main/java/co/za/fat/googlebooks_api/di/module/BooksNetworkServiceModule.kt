package co.za.fat.googlebooks_api.di.module

import co.za.fat.googlebooks_api.network.api.BooksNetworkService
import dagger.Binds
import dagger.Module

@Module
abstract class BooksNetworkServiceModule {
    @Binds
    abstract fun provideBooksNetworkService(booksNetworkService: BooksNetworkService): BooksNetworkService
}