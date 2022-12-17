package co.za.fat.googlebooks_api.network

import co.za.fat.googlebooks_api.network.responses.BookSearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface BooksNetworkService {
    @GET("v1/volumes")
    fun getBooksFromApi(
        @Query("q") searchTerm: String,
    ): Call<BookSearchResponse>

}