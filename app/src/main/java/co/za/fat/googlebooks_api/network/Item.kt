package co.za.fat.googlebooks_api.network

import co.za.fat.googlebooks_api.network.model.BookNetworkModel

data class Item(
    val kind: String,
    val volumeInfo: BookNetworkModel,
)