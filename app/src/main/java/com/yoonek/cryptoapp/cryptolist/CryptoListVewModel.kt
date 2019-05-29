package com.yoonek.cryptoapp.cryptolist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yoonek.cryptoapp.database.Crypto
import com.yoonek.cryptoapp.database.CryptoRepository
import io.realm.Realm

class CryptoListVewModel : ViewModel() {

    private var realm: Realm
    private var repository :CryptoRepository
      var cryptos:List<Crypto>


    init {

        cryptos = arrayListOf()
        realm = Realm.getDefaultInstance()
        repository = CryptoRepository(realm)
       // repository.refresh()

      cryptos =    repository.cryptoList()


    }



    override fun onCleared() {
        super.onCleared()
        realm.close()
    }

    private val _navigateToCryptoDetail = MutableLiveData<String>()

    val navigateToCryptoDetail:LiveData<String>
            get() = _navigateToCryptoDetail

    fun onCryptoClicked(string: String){
        _navigateToCryptoDetail.value = string
    }

    fun cryptoDetailNavigated(){
        _navigateToCryptoDetail.value = null
    }
}
