package com.example.datastorespace

import android.content.Context
import android.widget.Toast
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.datastorespace.App.Companion.dataStore
import com.example.datastorespace.repository.ImplRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MyDataStore(private val context: Context) {



    private val dataStore: DataStore<Preferences> = context.dataStore

     suspend fun savePerson(name: String,email:String) {

        dataStore.edit { personDataStore->
            personDataStore[stringPreferencesKey(name)] = email
        }

    }

     suspend fun getPerson(key: String): Flow<String> {
        val temp= dataStore.data.map { personDataStore ->
             personDataStore[stringPreferencesKey(key)] ?: "not found"
         }
         return temp
     }

}