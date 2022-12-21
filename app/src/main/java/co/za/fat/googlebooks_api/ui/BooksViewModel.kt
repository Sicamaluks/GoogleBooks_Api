package co.za.fat.googlebooks_api.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.za.fat.googlebooks_api.data.Book
import co.za.fat.googlebooks_api.network.api.BooksNetworkService
import co.za.fat.googlebooks_api.utils.BookFromNetworkModelMapper
import kotlinx.coroutines.launch
import javax.inject.Inject


class BooksViewModel @Inject constructor(
    booksNetworkService: BooksNetworkService
) : ViewModel() {

    private var searchTerm: String? = null
    private var _books: MutableLiveData<List<Book>> = MutableLiveData<List<Book>>()
    val books: LiveData<List<Book>> get() = _books

    fun updateSearchData(searchTerm: String) {
        this.searchTerm = searchTerm
    }

    init {

        viewModelScope.launch {
            Log.d("ViewModel", "booksViewModel: initiated ")
            val response = booksNetworkService.getBooksFromApi("spiderman").items

            val books = mutableListOf<Book>()
            for (item in response) {
                books.add(BookFromNetworkModelMapper().NetworkModelToBook(item.volumeInfo))
                println(BookFromNetworkModelMapper().NetworkModelToBook(item.volumeInfo))
            }
            _books.value = books

        }

    }
}

