package co.za.fat.googlebooks_api.di

import android.content.Context
import co.za.fat.googlebooks_api.di.module.AppModule
import co.za.fat.googlebooks_api.di.module.BooksViewModelFactoryModule
import co.za.fat.googlebooks_api.di.module.ViewModelModule
import co.za.fat.googlebooks_api.ui.BooksViewModel
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class, ViewModelModule::class, BooksViewModelFactoryModule::class])
@Singleton
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun booksViewModel(): BooksViewModel
}