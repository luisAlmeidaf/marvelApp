package com.example.marvelapp.domain.model

data class Stories(
    val available: Int,
    val collectionURI: String,
    val items: List<Any>,
    val returned: Int
)