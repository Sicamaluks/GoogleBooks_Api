package co.za.fat.googlebooks_api.data

import android.content.Context
import androidx.room.*
import co.za.fat.googlebooks_api.utils.BooksDatabaseMigrations
import co.za.fat.googlebooks_api.utils.Converters


@Database(
    entities = [Book::class], version = 6, exportSchema = true, autoMigrations =
    [AutoMigration(from = 5, to = 6, spec = BooksDatabaseMigrations::class)]
)

@TypeConverters(Converters::class)
abstract class BooksDatabase : RoomDatabase() {
    abstract fun bookDao(): BookDAO

    companion object {
        @Volatile
        private var INSTANCE: BooksDatabase? = null
        fun getDatabase(context: Context): BooksDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    BooksDatabase::class.java,
                    "books_database"
                )
                    .build()
                INSTANCE = instance

                instance
            }
        }
    }
}