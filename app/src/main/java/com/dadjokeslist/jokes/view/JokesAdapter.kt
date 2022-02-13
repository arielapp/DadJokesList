package com.dadjokeslist.jokes.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dadjokeslist.databinding.JokeItemBinding
import com.dadjokeslist.jokes.viewmodel.DadJokesViewModel

class JokesAdapter(private val viewModel: DadJokesViewModel): RecyclerView.Adapter<JokesAdapter.JokesViewHolder>() {

    inner class JokesViewHolder(val binding: JokeItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokesViewHolder {
        return JokesViewHolder(
            JokeItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: JokesViewHolder, position: Int) {
       holder.binding.apply {
            jokeTextview.text = viewModel.viewState.value?.joke?.get(position)
       }
    }

    override fun getItemCount(): Int {
        return viewModel.viewState.value?.joke?.size ?: 0
    }


}