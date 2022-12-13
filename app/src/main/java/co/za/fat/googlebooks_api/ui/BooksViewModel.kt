package co.za.fat.googlebooks_api.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import co.za.fat.googlebooks_api.data.Book
import co.za.fat.googlebooks_api.data.BooksDatabase
import co.za.fat.googlebooks_api.repository.BooksRepository

class BooksViewModel(application: Application) : AndroidViewModel(application) {
    lateinit var books: LiveData<List<Book>>

    private var _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val repository: BooksRepository
    suspend fun addBook(book: Book) {
        repository.addBook(book)
    }

    init {
        val bookDao = BooksDatabase.getDatabase(application).bookDao()
        repository = BooksRepository(bookDao)
        books = repository.getAllBooks
    }

    fun setLoadingState() {
        _isLoading.value = true
    }
}

//class BooksViewModelFactory(
//    private val bookRepository: BooksRepository
//) : ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(BooksViewModel::class.java)) {
//            @Suppress("UNCHECKED_CAST")
//            return BooksViewModel(bookRepository) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }
//}