package com.dadjokeslist.jokes.repository.remote

import com.dadjokeslist.jokes.api.JokesApi
import com.dadjokeslist.jokes.repository.responsemodels.Joke
import com.dadjokeslist.util.Result
import io.reactivex.Observable
import okhttp3.ResponseBody
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import retrofit2.Response

class JokesRemoteRepositoryImplTest {

    private lateinit var underTest: JokesRemoteRepositoryImpl

    @Mock
    private lateinit var api: JokesApi

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        underTest = JokesRemoteRepositoryImpl(api)
    }

    // region get Jokes tests

    @Test
    fun `given success response, when getting jokes, then return correct result`() {
        // Given
        val expectedValue = Joke("123", "great Joke", 200)

        `when`(api.getDadJokes()).thenReturn(
            Observable.just(
                Response.success(
                    200,
                    Joke(
                        "123",
                        "great Joke",
                        200
                    )
                )
            )
        )

        // When
        val actualResult = underTest.getJokes().test()

        // Then
        assertEquals(1, actualResult.valueCount())
        assertTrue(actualResult.values()[0] is Result.Success<*>)
        actualResult.assertValue {
            it.data?.id == expectedValue.id &&
                    it.data?.joke == expectedValue.joke &&
                    it.data?.status == expectedValue.status
        }
    }

    @Test
    fun `given error response, when getting jokes, then return correct result`() {
        // Given
        `when`(api.getDadJokes()).thenReturn(
            Observable.just(
                Response.error(
                    404, ResponseBody.create(
                        null, "error"
                    )
                )
            )
        )

        // When
        val actualResult = underTest.getJokes().test()

        // Then
        assertEquals(1, actualResult.valueCount())
        assertTrue(actualResult.values()[0] is Result.Fail<*>)
        actualResult.assertValue {
            it.message == "failed to fetch joke"
        }
    }

    // endregion get Jokes tests
}