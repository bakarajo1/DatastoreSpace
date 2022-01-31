package com.example.datastorespace

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

const val DataStore_NAME = "PERSON_DATA_STORE"

class App : Application() {

    companion object {
        private lateinit var context: App
        val Context.dataStore: DataStore<Preferences> by preferencesDataStore(DataStore_NAME)
    }

    override fun onCreate() {
        super.onCreate()
        context = this
       // val dataStore: MyDataStore = context.dataStore

    }
}