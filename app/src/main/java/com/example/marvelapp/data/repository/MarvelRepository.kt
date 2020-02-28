package com.example.marvelapp.data.repository

import com.example.marvelapp.domain.model.Data

interface MarvelRepository{
    //name: String, apiKey: String, ts: String, hash: String,
    suspend fun getCharacter(offset: Int): Data

}