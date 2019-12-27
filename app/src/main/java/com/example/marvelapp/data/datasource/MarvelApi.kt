package com.example.marvelapp.data.datasource

import com.example.marvelapp.domain.model.Data
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelApi{

    @GET("characters")
    fun getCharacters(@Query("ts") ts: String,
                      @Query("apikey") apiKey: String,
                      @Query("hash") hash: String,
                      @Query("limit") limit: String = "99",
                      @Query("offset") offset: String = "203"): Call<Data>


}

interface OnGetMarvelCallback{
    fun onSuccess(marvelResponse: Data)
    fun onError()
}