package com.example.cryptoapp.repo

import android.util.Log
import io.realm.Realm
import io.realm.RealmResults
import io.realm.kotlin.where
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CryptoRepository(val realm:Realm){

    fun refresh(){
        val call : Call<List<Crypto>> = CryptoApi.retrofitService.getCryptos()

        call.enqueue(object : Callback<List<Crypto>> {
            override fun onFailure(call: Call<List<Crypto>>, t: Throwable) {

            }

            override fun onResponse(call: Call<List<Crypto>>, response: Response<List<Crypto>>) {
                if (response.isSuccessful){

                    save(response.body()!!)
                    Log.e("suss","${response.body()!!.size}")
                }
            }
        })
    }

    private fun save(cryptos:List<Crypto>){
        realm.beginTransaction()
        realm.insertOrUpdate(cryptos)
        realm.commitTransaction()
    }

    fun cryptoList():RealmResults<Crypto>{
      return realm.where(Crypto::class.java).findAllAsync()

    }

    fun getCrypto(name:String): Crypto? {
        return realm.where<Crypto>()
            .equalTo("name",name).findFirst()


    }


}