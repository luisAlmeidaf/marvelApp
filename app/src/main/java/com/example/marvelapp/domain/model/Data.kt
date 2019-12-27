package com.example.marvelapp.domain.model

data class Data(
    val attributionHTML: String,
    val attributionText: String,
    val code: Int,
    val copyright: String,
    val `data`: ReturnData,
    val etag: String,
    val status: String
)