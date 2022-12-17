package co.za.fat.googlebooks_api.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDAO {
    @Query("SELECT * FROM books ORDER BY publishedDate ASC")
    fun getAllBooks(): Flow<List<Book>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addBook(book: Book)

    @Query("Delete from books")
    suspend fun deleteAllBooks()
}