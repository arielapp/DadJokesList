package com.dadjokeslist.util

sealed class Result<T>(val data: T?, val message: String?) {

    class Success<T>(data: T): Result<T>(data, null)
    class Fail<T>(message: String): Result<T>(null, message)
}
