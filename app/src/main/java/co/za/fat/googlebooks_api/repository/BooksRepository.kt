package co.za.fat.googlebooks_api.repository

import androidx.lifecycle.LiveData
import co.za.fat.googlebooks_api.data.Book
import co.za.fat.googlebooks_api.data.BookDAO

class BooksRepository(private val bookDAO: BookDAO) {
    val getAllBooks: LiveData<List<Book>> =
        bookDAO.getAllBooks()

    suspend fun addBook(book: Book) {
        bookDAO.addBook(book)
    }
}