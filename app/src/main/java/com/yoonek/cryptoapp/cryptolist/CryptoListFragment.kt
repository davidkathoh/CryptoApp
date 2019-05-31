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
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yoonek.cryptoapp.R
import com.yoonek.cryptoapp.database.Crypto
import io.realm.RealmChangeListener
import io.realm.RealmResults

class CryptoListFragment : Fragment() {

    private lateinit var vewModel: CryptoListVewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var crytoAdaper: CrytoAdaper

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_crypto_list, container, false)



        recyclerView = view.findViewById(R.id.recycle_view)
        vewModel = ViewModelProviders.of(this).get(CryptoListVewModel::class.java)

        crytoAdaper = CrytoAdaper(CrytoAdaper.CryptoListener {
            vewModel.onCryptoClicked(it)
        })
        recyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = crytoAdaper
        }

        crytoAdaper.setCryptoList(vewModel.getCryptos())

        vewModel.navigateToCryptoDetail.observe(this, Observer { name ->
            name?.let {
                this.findNavController().navigate(CryptoListFragmentDirections.actionCryptoListFragmentToCryptoDetailFragment(name))
                vewModel.cryptoDetailNavigated()
            }

        })

        return view
    }

    //update the crypto list when it change
    private var cryptoChangeListener = RealmChangeListener<RealmResults<Crypto>> {
        crytoAdaper.setCryptoList(it)
    }

    override fun onStart() {
        super.onStart()
        vewModel.getCryptos().addChangeListener(cryptoChangeListener)
    }

    override fun onStop() {
        super.onStop()
        vewModel.getCryptos().removeChangeListener(cryptoChangeListener)
    }


}
