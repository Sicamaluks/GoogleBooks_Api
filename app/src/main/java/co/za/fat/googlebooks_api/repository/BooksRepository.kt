package co.za.fat.googlebooks_api.repository

import androidx.room.withTransaction
import co.za.fat.googlebooks_api.data.BooksDatabase
import co.za.fat.googlebooks_api.network.BooksNetworkService
import co.za.fat.googlebooks_api.utils.BookFromNetworkModelMapper
import co.za.fat.googlebooks_api.utils.network_bound_resource.networkBoundResource
import kotlinx.coroutines.delay

class BooksRepository(
    private val booksDatabase: BooksDatabase,
    private val booksNetworkService: BooksNetworkService,
    private val bookFromNetworkModelMapper: BookFromNetworkModelMapper
) {
    private val booksDAO = booksDatabase.bookDao()

    fun getBooks(searchTerm: String) = networkBoundResource(
        query = {
            booksDAO.getAllBooks()
        },
        fetch = {
            delay(2000)
            booksNetworkService.getBooksFromApi(searchTerm)
        },
        saveFetchResult = { books ->
            val booksList = books.execute().body()?.items?.get(1)?.volumeInfo
            booksDatabase.withTransaction {
                booksDAO.deleteAllBooks()

                if (booksList != null) {
                    booksDAO.addBook(bookFromNetworkModelMapper.NetworkModelToBook(booksList))
                }


            }
        }
    )
}