package com.example.datastorespace

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.datastorespace.repository.ImplRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch


class ActivityViewModel(private  val implRepository: ImplRepository):ViewModel() {


    private val _user: MutableLiveData<String> = MutableLiveData("Hello")
    val user: MutableLiveData<String> = _user

    suspend fun saveData(name: String,email:String) {
        coroutineScope {
            launch(Dispatchers.IO) {
                implRepository.savePerson(name,email)
            }
        }
    }


     suspend fun retrieveDate(key:String) {


        coroutineScope {
            launch(Dispatchers.IO) {
                _user.postValue(implRepository.getPerson(key).first())
//                implRepository.getPerson(key).collect(){
//                    user.postValue(it)
//
//                }
            }
        }
    }


}
class MyViewModelFactory(private val repository: ImplRepository)
    : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(ActivityViewModel::class.java)) {
            return ActivityViewModel(repository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}