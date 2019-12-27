package com.example.marvelapp.data.repository

import com.example.marvelapp.data.datasource.OnGetMarvelCallback

interface MarvelRepository{
    //name: String, apiKey: String, ts: String, hash: String,
    fun getCharacter(offset: Int, callback: OnGetMarvelCallback)

}