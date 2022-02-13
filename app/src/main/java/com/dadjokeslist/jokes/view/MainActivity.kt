package com.dadjokeslist.jokes.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dadjokeslist.databinding.ActivityMainBinding
import com.dadjokeslist.di.DaggerAppComponent
import com.dadjokeslist.di.ViewModelFactory
import com.dadjokeslist.jokes.viewmodel.DadJokesViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: DadJokesViewModel

    private lateinit var binding: ActivityMainBinding

    private lateinit var adapter: JokesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, viewModelFactory).get(DadJokesViewModel::class.java)

        initObservers()
    }

    private fun initObservers() {
        viewModel.viewState.observe(this, {
            adapter = JokesAdapter(viewModel)
            with(binding.jokesRecyclerview) {
                this.layoutManager = LinearLayoutManager(this@MainActivity)
                this.adapter = adapter
            }
        })
    }

    private val appComponent by lazy {
        DaggerAppComponent
            .builder()
            .build()
    }
}