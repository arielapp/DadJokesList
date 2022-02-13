package com.dadjokeslist.jokes.usecase

import com.dadjokeslist.jokes.repository.remote.JokesRemoteRepository
import com.dadjokeslist.jokes.repository.responsemodels.Joke
import com.dadjokeslist.util.Result
import io.reactivex.Observable

class DadJokesUseCaseImpl(
    private val repository: JokesRemoteRepository
): DadJokesUseCase {

    override fun getDadJoke(): Observable<Result<Joke>> {
        return repository.getJokes()
    }


}