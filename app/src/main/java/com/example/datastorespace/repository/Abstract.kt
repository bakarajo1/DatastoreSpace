package com.example.datastorespace.repository

import kotlinx.coroutines.flow.Flow

interface Abstract {

    suspend fun savePerson(name: String, email:String)

    suspend fun getPerson(key:String):Flow<String>
}