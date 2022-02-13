package com.dadjokeslist.jokes.repository.remote

import com.dadjokeslist.jokes.api.JokesApi
import com.dadjokeslist.jokes.repository.responsemodels.Joke
import com.dadjokeslist.util.Result
import io.reactivex.Observable
import javax.inject.Inject

class JokesRemoteRepositoryImpl @Inject constructor(
   private val api: JokesApi
): JokesRemoteRepository {

    override fun getJokes(): Observable<Result<Joke>> {
        return try {
            api.getDadJokes().map {
                if (it.isSuccessful) {
                    Result.Success(
                        it.body() ?: Joke("", "", 0)
                    )
                } else {
                    Result.Fail("failed to fetch joke")
                }
            }

        } catch (e: Exception) {
            Observable.just(Result.Fail(e.message.toString()))
        }


    }


}