package co.za.fat.googlebooks_api.network

import co.za.fat.googlebooks_api.data.Book
import co.za.fat.googlebooks_api.network.responses.BookSearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface BooksNetworkService {
    @GET("v1/volumes")
    fun searchBooks(
//        @QueryMap searchTerm: Map<String, String>,
        @Query("q") searchTerm: String,
//        @Query("key") key: String,
    ): Call<BookSearchResponse>

}