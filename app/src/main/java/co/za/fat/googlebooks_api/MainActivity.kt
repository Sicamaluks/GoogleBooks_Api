package co.za.fat.googlebooks_api

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import co.za.fat.googlebooks_api.databinding.ActivityMainBinding
import co.za.fat.googlebooks_api.ui.BooksAdapter
import co.za.fat.googlebooks_api.ui.BooksViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


private const val BASE_URL = "https://www.googleapis.com/books/"
private const val TAG = "MainActivity"


class MainActivity() :
    AppCompatActivity() {
    private lateinit var booksAdapter: BooksAdapter
    private lateinit var booksViewModel: BooksViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        val appComponent = (applicationContext as GoogleBooksApplication).appComponent

        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.activityMainToolbar)
        booksViewModel = appComponent.booksViewModel()
        val searchTerm = intent.getStringExtra("searchTerm")
        booksAdapter = BooksAdapter(this)

        booksViewModel.updateSearchData(searchTerm!!)

        booksViewModel.books.observe(this@MainActivity) {
            booksAdapter.setData(it)
        }
        CoroutineScope(Dispatchers.IO).launch {

        }
        binding.apply {

            rwBooksList.apply {
                adapter = booksAdapter
                layoutManager = LinearLayoutManager(this@MainActivity)
            }


        }
    }


}