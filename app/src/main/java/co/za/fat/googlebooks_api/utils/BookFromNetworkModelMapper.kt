package co.za.fat.googlebooks_api.utils

import co.za.fat.googlebooks_api.data.Book
import co.za.fat.googlebooks_api.network.model.BookNetworkModel

class BookFromNetworkModelMapper : DataMapper {
    override fun NetworkModelToBook(bookNetworkModel: BookNetworkModel): Book {
        return Book(
            id = bookNetworkModel.id,
            title = bookNetworkModel.title ?: "",
            subtitle = bookNetworkModel.subtitle ?: "",
            authors = bookNetworkModel.authors ?: arrayListOf(),
            publisher = bookNetworkModel.publisher ?: "",
            publishedDate = bookNetworkModel.publishedDate ?: "",
            description = bookNetworkModel.description ?: "",
            pageCount = bookNetworkModel.pageCount,
            printType = bookNetworkModel.printType,
            categories = bookNetworkModel.categories ?: arrayListOf(),
            imageLinks = bookNetworkModel.imageLinks["thumbnail"].toString().replace("\"", ""),
            keyWords = arrayListOf()
        )
    }
}