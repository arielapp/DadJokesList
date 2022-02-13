package com.dadjokeslist.jokes.api

import com.dadjokeslist.jokes.repository.responsemodels.Joke
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET

interface JokesApi {

    @GET("/")
    fun getDadJokes(): Observable<Response<Joke>>
}