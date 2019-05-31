package com.yoonek.cryptoapp.cryptodetail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yoonek.cryptoapp.database.Crypto
import com.yoonek.cryptoapp.database.CryptoRepository
import io.realm.Realm

class CryptoDetailViewModel(cryptoName:String) :ViewModel() {

    private var realm: Realm = Realm.getDefaultInstance()
    private var repository : CryptoRepository
    lateinit var crypto:Crypto

    init {
        repository = CryptoRepository(realm)
         repository.getCrypto(cryptoName)?.let {
             crypto = it
         }
    }




    class CryptoDetailViewModelFactory(private val cryptoName: String):ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(CryptoDetailViewModel::class.java)){
                return CryptoDetailViewModel(cryptoName) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }

    }
}