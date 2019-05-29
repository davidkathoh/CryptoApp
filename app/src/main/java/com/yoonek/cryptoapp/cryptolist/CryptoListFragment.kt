package com.yoonek.cryptoapp.cryptolist


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.yoonek.cryptoapp.R
import com.yoonek.cryptoapp.database.Crypto
import com.yoonek.cryptoapp.database.CryptoApi
import io.realm.Realm
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CryptoListFragment : Fragment() {

    private lateinit var vewModel: CryptoListVewModel
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_crypto_list, container, false)
        recyclerView = view.findViewById(R.id.recycle_view)
       vewModel = ViewModelProviders.of(this).get(CryptoListVewModel::class.java)

        recyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = CrytoAdaper(CrytoAdaper.CryptoListener {
                vewModel.onCryptoClicked(it)
            },vewModel.cryptos)
        }


        vewModel.navigateToCryptoDetail.observe(this, Observer { str ->
            str?.let {
                Toast.makeText(context,"$it",Toast.LENGTH_SHORT).show()
                vewModel.cryptoDetailNavigated()
            }

        })

        return view
    }


}
