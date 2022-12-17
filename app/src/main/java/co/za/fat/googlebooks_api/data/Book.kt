package co.za.fat.googlebooks_api.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "books", indices = [Index(value = ["title"], unique = true)])
data class Book(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    @ColumnInfo(defaultValue = "no subtitle")
    var subtitle: String,
    @ColumnInfo(defaultValue = "authors unknown")
    val authors: ArrayList<String> = arrayListOf(),
    @ColumnInfo(defaultValue = "")
    var publisher: String,
    @ColumnInfo(defaultValue = "published date unknown")
    val publishedDate: String,
    @ColumnInfo(defaultValue = "no description")
    val description: String,
    val pageCount: Int,
    val printType: String?,
    @ColumnInfo(defaultValue = "categories unknown")
    val categories: ArrayList<String> = arrayListOf(),
    @ColumnInfo(defaultValue = "")
    val imageLinks: String,
    @ColumnInfo(defaultValue = "add key words")
    var keyWords: ArrayList<String> = arrayListOf()
) : java.io.Serializable


