package co.za.fat.googlebooks_api.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import co.za.fat.googlebooks_api.data.Book
import co.za.fat.googlebooks_api.repository.BooksRepository

class BooksViewModel(repository: BooksRepository, application: Application) :
    AndroidViewModel(application) {
    private var _books: MutableLiveData<List<Book>> = MutableLiveData()
    val books: LiveData<List<Book>> get() = _books


    private var _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        _books = repository.getBooks("spidernman") as MutableLiveData<List<Book>>
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