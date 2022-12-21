package co.za.fat.googlebooks_api

import android.app.Application
import co.za.fat.googlebooks_api.data.BooksDatabase
import co.za.fat.googlebooks_api.di.AppComponent
import co.za.fat.googlebooks_api.di.DaggerAppComponent


open class GoogleBooksApplication : Application() {
    val database: BooksDatabase by lazy { BooksDatabase.getDatabase(this) }

    val appComponent: AppComponent by lazy {
        // Creates an instance of AppComponent using its Factory constructor
        // We pass the applicationContext that will be used as Context in the graph
        DaggerAppComponent.factory().create(applicationContext)
    }

}