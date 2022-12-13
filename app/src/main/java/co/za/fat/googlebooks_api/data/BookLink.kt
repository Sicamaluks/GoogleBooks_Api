package co.za.fat.googlebooks_api.data

import android.net.Uri
import androidx.room.Embedded
import androidx.room.Relation

data class BookLink(
    @Embedded val bookUri: Uri,
    @Relation(
        parentColumn = "bookLinkId",
        entityColumn = "id"
    )
    val book: Book
)