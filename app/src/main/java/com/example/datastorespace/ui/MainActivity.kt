package com.example.datastorespace.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.datastorespace.ActivityViewModel
import com.example.datastorespace.MyDataStore
import com.example.datastorespace.MyViewModelFactory
import com.example.datastorespace.databinding.ActivityMainBinding
import com.example.datastorespace.repository.ImplRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {


    lateinit var binding: ActivityMainBinding
   // private lateinit var viewModel: ActivityViewModel
   // val model: vvu by view()
   private val viewModel by lazy {
       ViewModelProvider(this, MyViewModelFactory(ImplRepository(MyDataStore(this)))).get(
           ActivityViewModel::class.java)
   }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //viewModel = ViewModelProvider(this)[ActivityViewModel::class.java]


//        val viewModel = ViewModelProvider(this).get(ActivityViewModel::class.java)
        setListeners()
        setObserver()


    }

    private fun setObserver() {
        viewModel.user.observe(this, Observer {
            binding.textView.text=it
        })
    }

    private fun setListeners() {


        binding.saveButton.setOnClickListener {
            runBlocking { launch( Dispatchers.IO) {
                viewModel.saveData(binding.editTextTextPersonName.text.toString(),binding.editTextTextPersonValue.text.toString())

            } }
        }

        binding.findButton.setOnClickListener {

            var foundValue=""
            runBlocking { launch( Dispatchers.IO) {
                 viewModel.retrieveDate(binding.editTextTextCheckKey.text.toString())

            } }
            

        }


    }
}