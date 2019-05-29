package com.yoonek

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

class CryptoApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        Realm.setDefaultConfiguration(
            RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build())
    }

}