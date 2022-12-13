package co.za.fat.googlebooks_api.network.model

import com.google.gson.JsonObject


class BookNetworkModel(
    val id: Int,
    val title: String,
    var subtitle: String,
    val authors: ArrayList<String>,
    var publisher: String,
    val publishedDate: String,
    val description: String,
    val pageCount: Int,
    val printType: String,
    val categories: ArrayList<String>,
    val imageLinks: JsonObject,
    val keyWords: ArrayList<String>,
)