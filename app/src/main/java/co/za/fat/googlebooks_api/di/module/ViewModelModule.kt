package co.za.fat.googlebooks_api.di.module

import androidx.lifecycle.ViewModel
import co.za.fat.googlebooks_api.di.annotation.ViewModelKey
import co.za.fat.googlebooks_api.ui.BooksViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(BooksViewModel::class)
    abstract fun bindBooksViewModel(booksViewModel: BooksViewModel): ViewModel
}