package co.za.fat.googlebooks_api.utils

import co.za.fat.googlebooks_api.data.Book
import co.za.fat.googlebooks_api.network.model.BookNetworkModel

interface DataMapper {
    fun NetworkModelToBook(bookNetworkModel: BookNetworkModel): Book
}