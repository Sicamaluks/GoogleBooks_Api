package co.za.fat.googlebooks_api

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.za.fat.googlebooks_api.network.BooksNetworkService
import co.za.fat.googlebooks_api.network.responses.BookSearchResponse
import co.za.fat.googlebooks_api.ui.BooksAdapter
import co.za.fat.googlebooks_api.ui.BooksViewModel
import co.za.fat.googlebooks_api.utils.BookFromNetworkModelMapper
import com.google.gson.GsonBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


private const val BASE_URL = "https://www.googleapis.com/books/"
private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var booksAdapter: BooksAdapter
    private lateinit var viewModel: BooksViewModel
    private lateinit var pb: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(findViewById(R.id.activity_main_toolbar))

        val pb = findViewById<ProgressBar>(R.id.pb)

        //RecyclerView
        recyclerView = findViewById(R.id.rw_books_list)
        booksAdapter = BooksAdapter(this)

        recyclerView.adapter = booksAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        //View model
        viewModel = ViewModelProvider(this)[BooksViewModel::class.java]
        viewModel.isLoading.observe(this) {

            pb.visibility = View.VISIBLE

        }
        val searchTerm = intent.getStringExtra("searchTerm")
        viewModel.books.observe(this) { book ->
            booksAdapter.setData(book, searchTerm.toString(), viewModel)
        }


        //Retrofit Instance
        val booksNetworkService = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(BooksNetworkService::class.java)

//            ?.let { booksNetworkService.searchBooks(it) }
        booksNetworkService
            .getBooksFromApi(searchTerm.toString())
            .enqueue(object : Callback<BookSearchResponse> {
                override fun onResponse(
                    call: Call<BookSearchResponse>,
                    response: Response<BookSearchResponse>
                ) {
                    if (response.isSuccessful) {
                        Log.i("MainActivity", "onResponse: ${response.body()?.items}")
                        for (item in response.body()?.items!!) {
                            CoroutineScope(Dispatchers.IO).launch {
                                val book =
                                    BookFromNetworkModelMapper().NetworkModelToBook(item.volumeInfo)
                                book.keyWords.add(searchTerm.toString())
                                viewModel.books


                            }

                        }
                    }
                }

                override fun onFailure(call: Call<BookSearchResponse>, t: Throwable) {
                    Log.d(TAG, "onFailure: ${t.cause} ")
                    Toast.makeText(this@MainActivity, "Fail to get the data...", Toast.LENGTH_SHORT)
                        .show()
                }

            })


//        }


    }


}