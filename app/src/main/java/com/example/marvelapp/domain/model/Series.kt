package com.example.marvelapp.domain.model

data class Series(
    val available: Int,
    val collectionURI: String,
    val items: List<Any>,
    val returned: Int
)