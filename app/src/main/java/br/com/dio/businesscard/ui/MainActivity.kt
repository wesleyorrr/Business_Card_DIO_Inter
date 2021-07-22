package br.com.dio.businesscard.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import br.com.dio.businesscard.App


import br.com.dio.businesscard.databinding.ActivityMainBinding
import br.com.dio.businesscard.ultil.Image

class MainActivity : AppCompatActivity() {
    private val binding by lazy {ActivityMainBinding.inflate(layoutInflater)}
    private val mainViewModel: MainViewModel by viewModels{
        MainViewModelFactory((application as App).repository)
    }
    private val adapter by lazy { BusinessCardAdapter() }


        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
            binding.rvCards.adapter = adapter
            getAllBusinessCard()
            InsertListenes()
    }
 private fun InsertListenes(){
     binding.fab.setOnClickListener {
        val intent = Intent(this@MainActivity, AddBusinessCardActivity::class.java)

         startActivity(intent)
     }
     adapter.listenerShare = { card ->
         Image.share(this@MainActivity, card)
     }
 }
    private fun getAllBusinessCard(){

        mainViewModel.getAll().observe(this,{businessCards ->
            adapter.submitList(businessCards)
        })
    }

 }

