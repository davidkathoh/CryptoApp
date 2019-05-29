package com.yoonek.cryptoapp.database

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers


private  const val BASE_URL = "https://api.coinmarketcap.com/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()


private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()


interface CryptoApiService{
    @Headers("X-CMC_PRO_API_KEY:241e6459-a362-43a1-84a7-a7ea56c9795d")
    @GET("v1/ticker/?start=0&limit=500")
    fun getCryptos():Call<List<Crypto>>
}
object CryptoApi {
    val retrofitService: CryptoApiService by lazy {
        retrofit.create(CryptoApiService::class.java)
    }
}