package co.za.fat.googlebooks_api

import android.app.Application
import co.za.fat.googlebooks_api.data.BooksDatabase

class GoogleBooksApplication: Application() {
    val database: BooksDatabase by lazy { BooksDatabase.getDatabase(this) }
}