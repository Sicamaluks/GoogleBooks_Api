package co.za.fat.googlebooks_api.network.api

import co.za.fat.googlebooks_api.network.responses.BookSearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface BooksNetworkService {
    companion object {
        const val BASE_URL = "https://www.googleapis.com/books/"
    }

    @GET("v1/volumes")
    suspend fun getBooksFromApi(
        @Query("q") searchTerm: String,
    ): BookSearchResponse

}