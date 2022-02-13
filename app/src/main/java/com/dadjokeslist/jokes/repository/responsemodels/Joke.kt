package com.dadjokeslist.jokes.repository.responsemodels


import com.google.gson.annotations.SerializedName

data class Joke(
    @SerializedName("id")
    val id: String,
    @SerializedName("joke")
    val joke: String,
    @SerializedName("status")
    val status: Int
)