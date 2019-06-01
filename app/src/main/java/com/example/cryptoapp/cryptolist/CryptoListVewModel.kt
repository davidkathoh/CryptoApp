package com.example.cryptoapp.cryptolist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cryptoapp.repo.Crypto
import com.example.cryptoapp.repo.CryptoRepository
import io.realm.Realm
import io.realm.RealmResults

class CryptoListVewModel : ViewModel() {

    private var realm: Realm = Realm.getDefaultInstance()
    private var repository :CryptoRepository
    private var cryptos:RealmResults<Crypto>

    init {

        repository = CryptoRepository(realm)
        repository.refresh()
        cryptos =    repository.cryptoList()

    }

    override fun onCleared() {
        super.onCleared()
        realm.close()
        cryptos.removeAllChangeListeners()
    }

    fun getCryptos():RealmResults<Crypto>{
        return cryptos
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
