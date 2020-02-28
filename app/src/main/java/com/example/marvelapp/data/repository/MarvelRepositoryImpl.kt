package com.example.marvelapp.data.repository

import androidx.lifecycle.MutableLiveData
import com.example.marvelapp.BuildConfig
import com.example.marvelapp.data.datasource.MarvelApi
import com.example.marvelapp.domain.model.Data
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class MarvelRepositoryImpl: MarvelRepository, KoinComponent {

    private val service: MarvelApi by inject()
    private var heroesData: MutableLiveData<Data> = MutableLiveData()

    companion object {
        const val BASE_URL = BuildConfig.BASE_URL
        val PUBLIC_KEY = BuildConfig.MarvelPublicKey
        val PRIVATE_KEY = BuildConfig.MarvelPrivateKey
    }

    override suspend fun getCharacter(offset: Int): Data {
        val ts = System.currentTimeMillis().toString()
        var hash = getMd5(ts)

        return service.getCharacters(ts, PUBLIC_KEY, hash, offset = offset.toString())
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