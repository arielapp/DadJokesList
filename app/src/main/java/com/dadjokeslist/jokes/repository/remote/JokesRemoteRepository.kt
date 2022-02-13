package com.dadjokeslist.jokes.repository.remote

import com.dadjokeslist.jokes.repository.responsemodels.Joke
import com.dadjokeslist.util.Result
import io.reactivex.Observable

interface JokesRemoteRepository {

    fun getJokes(): Observable<Result<Joke>>
}