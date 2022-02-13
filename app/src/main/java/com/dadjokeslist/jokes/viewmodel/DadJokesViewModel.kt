package com.dadjokeslist.jokes.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dadjokeslist.jokes.usecase.DadJokesUseCase
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class DadJokesViewModel @Inject constructor(
    private val useCase: DadJokesUseCase
) : ViewModel() {

    private val mutableViewStates = MutableLiveData<DadJokesViewState>()
    val viewState = mutableViewStates

    init {
        CompositeDisposable().add(
            useCase.getDadJoke()
                .subscribe({
                    mutableViewStates.value = DadJokesViewState(
                        listOf(it.data?.joke ?: "")
                    )
                },
                    {

                    }
                )
        )

    }

}