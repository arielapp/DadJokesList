package com.dadjokeslist.jokes.usecase

import com.dadjokeslist.jokes.repository.remote.JokesRemoteRepository
import com.dadjokeslist.jokes.repository.responsemodels.Joke
import com.dadjokeslist.util.Result
import io.reactivex.Observable
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class DadJokesUseCaseImplTest {

    private lateinit var underTest: DadJokesUseCaseImpl

    @Mock
    private lateinit var repository: JokesRemoteRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        underTest = DadJokesUseCaseImpl(repository)
    }

    // region get Jokes tests

    @Test
    fun `given success value from repository, when getting dad jokes, then return success value`() {
        // Given
        `when`(repository.getJokes()).thenReturn(
            Observable.just(Result.Success(Joke("12", "joke",200)))
        )

        // When
        val actualResult = underTest.getDadJoke().test()

        // Then
        assertEquals(1, actualResult.valueCount())
        assertTrue(actualResult.values()[0] is Result.Success<*>)
        actualResult.assertValue{
            it.data?.id == "12" &&
                    it.data?.joke == "joke" &&
                    it.data?.status == 200
        }
    }

    @Test
    fun `given fail value from repository, when getting dad jokes, then return success value`() {
        // Given
        `when`(repository.getJokes()).thenReturn(
            Observable.just(Result.Fail("error"))
        )

        // When
        val actualResult = underTest.getDadJoke().test()

        // Then
        assertEquals(1, actualResult.valueCount())
        assertTrue(actualResult.values()[0] is Result.Fail<*>)
        actualResult.assertValue{
            it.message == "error"
        }
    }

    // endregion get Jokes tests
}