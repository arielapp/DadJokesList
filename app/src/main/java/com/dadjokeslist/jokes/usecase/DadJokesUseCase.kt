package com.dadjokeslist.jokes.usecase

import com.dadjokeslist.jokes.repository.responsemodels.Joke
import com.dadjokeslist.util.Result
import io.reactivex.Observable

interface DadJokesUseCase {

    fun getDadJoke(): Observable<Result<Joke>>
}