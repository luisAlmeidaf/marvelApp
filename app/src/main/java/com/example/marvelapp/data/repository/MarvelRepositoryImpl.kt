package com.example.marvelapp.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.marvelapp.BuildConfig
import com.example.marvelapp.data.datasource.MarvelApi
import com.example.marvelapp.data.datasource.OnGetMarvelCallback
import com.example.marvelapp.domain.model.Data
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class MarvelRepositoryImpl: MarvelRepository {

    private var service: MarvelApi
    private var heroesData: MutableLiveData<Data> = MutableLiveData()

    companion object {
        const val BASE_URL = BuildConfig.BASE_URL
        val PUBLIC_KEY = BuildConfig.MarvelPublicKey
        val PRIVATE_KEY = BuildConfig.MarvelPrivateKey
    }

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create(MarvelApi::class.java)

    }

    override fun getCharacter(offset: Int, callback: OnGetMarvelCallback) {
        val ts = System.currentTimeMillis().toString()
        var hash = getMd5(ts)

        service.getCharacters(ts, PUBLIC_KEY, hash, offset = offset.toString())
            .enqueue(object : Callback<Data> {
                override fun onResponse(call: Call<Data>, response: Response<Data>) {

                    if (response.isSuccessful){
                        if (response.body() != null){
                            heroesData.postValue(response.body())
                            callback.onSuccess(response.body()!!)
                        } else {
                            callback.onError()
                            Log.e("Response", " response null")
                        }

                    } else {
                        callback.onError()
                        Log.e("Response", response.raw().networkResponse.toString())
                    }

                }

                override fun onFailure(call: Call<Data>, t: Throwable) {
                    callback.onError()
                    t.printStackTrace()
                    Log.e("Response", javaClass.simpleName + " not response 2 " + t)
                }
            })
    }

    private fun getMd5(ts: String): String {
        try {

            val md = MessageDigest.getInstance("MD5")

            val messageDigest = md.digest(ts.toByteArray()
                    + PRIVATE_KEY.toByteArray()
                    + PUBLIC_KEY.toByteArray())

            val no = BigInteger(1, messageDigest)

            var hashtext = no.toString(16)
            while (hashtext.length < 32) {
                hashtext = "0$hashtext"
            }
            return hashtext
        } catch (e: NoSuchAlgorithmException) {
            throw RuntimeException(e)
        }
    }
}