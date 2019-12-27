package com.example.marvelapp.domain.model

data class ReturnData(
    val count: Int,
    val limit: Int,
    val offset: Int,
    val results: List<Result>,
    val total: Int
)