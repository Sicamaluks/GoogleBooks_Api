package co.za.fat.googlebooks_api.network.responses

import co.za.fat.googlebooks_api.network.Item
import com.google.gson.annotations.SerializedName

class BookSearchResponse(
    @SerializedName("kind")
    val kind: String,
    @SerializedName("totalItems")
    val totalItems: Int,
    @SerializedName("items")
    val items: List<Item>
)