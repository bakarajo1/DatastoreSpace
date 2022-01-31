package com.example.datastorespace.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.datastorespace.MyDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map





class ImplRepository(private val dataStore: MyDataStore) : Abstract {



    override suspend fun savePerson(name: String,email:String) {
        dataStore.savePerson(name,email)

    }

    override suspend fun getPerson(key:String):Flow<String>{
        return dataStore.getPerson(key)
    }

    }
