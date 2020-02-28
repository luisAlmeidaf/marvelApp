package com.example.marvelapp

import com.example.marvelapp.data.datasource.MarvelApi
import com.example.marvelapp.domain.di.koin.modules.networkModule
import com.example.marvelapp.domain.di.koin.modules.uiModule
import junit.framework.Assert.assertNotNull
import junit.framework.Assert.assertTrue
import okhttp3.OkHttpClient
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.get
import org.koin.test.inject
import org.koin.core.qualifier.named
import org.koin.test.KoinTest
import retrofit2.Retrofit


class NetworkTest: KoinTest {

    private val api: MarvelApi by inject()
    private val retrofit: Retrofit by inject()
    private val okHttpClient: OkHttpClient by inject()
    private val baseUrl: String by lazy { get(named("BASE_URL")) as String}

    @Before
    fun setup(){
        startKoin {
            modules(listOf(uiModule, networkModule))
        }
    }

    @After
    fun tearDown(){
        stopKoin()
    }

    @Test
    fun `should test if retrofit was created`(){
        assertNotNull(retrofit)
        assert(baseUrl == BuildConfig.BASE_URL)
    }

    @Test
    fun `should test if api was created`(){
        assertNotNull(api)
    }

    @Test
    fun `should test if okhttpinterceptor was created and running with parameters added by user`(){
        assertNotNull(okHttpClient)
        assertTrue(okHttpClient.connectTimeoutMillis == 30000)
        assertTrue(okHttpClient.readTimeoutMillis == 30000)
        assertTrue(okHttpClient.writeTimeoutMillis == 30000)
        assertTrue(okHttpClient.interceptors.size == 1)
    }
}