package com.yoonek.cryptoapp.database

import android.util.Log
import io.realm.Realm
import io.realm.RealmResults
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
                    Log.e("suss","sussess")
                    save(response.body()!!) }
            }
        })
    }

    private fun save(cryptos:List<Crypto>){
        realm.beginTransaction()
        realm.insertOrUpdate(cryptos)
        realm.commitTransaction()
    }

    fun cryptoList():List<Crypto>{
      val results:  RealmResults<Crypto> =realm.where(Crypto::class.java).findAll()
        return realm.copyFromRealm(results)
    }



}