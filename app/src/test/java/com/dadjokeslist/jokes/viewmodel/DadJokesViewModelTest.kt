package com.dadjokeslist.jokes.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.dadjokeslist.jokes.repository.responsemodels.Joke
import com.dadjokeslist.jokes.usecase.DadJokesUseCase
import com.dadjokeslist.util.Result
import io.reactivex.Observable
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class DadJokesViewModelTest {


    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var underTest: DadJokesViewModel

    @Mock
    private lateinit var useCase: DadJokesUseCase

    @Captor
    private lateinit var viewState: ArgumentCaptor<DadJokesViewState>

    @Mock
    private lateinit var viewStateObserver: Observer<DadJokesViewState>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @After
    fun tearDown() {
        underTest.viewState.removeObserver(viewStateObserver)
    }

    // region init tests

    @Test
    fun `given success result from useCase, when initializing viewModel, then viewState values are set correctly`() {
        // Given
        `when`(useCase.getDadJoke()).thenReturn(
            Observable.just(
                Result.Success(
                    Joke("1234", "some Joke", 200)
                )
            )
        )

        // When
        initViewModel()

        // Then
        with(viewState) {
            verify(viewStateObserver, times((1))).onChanged(capture())
            assertEquals(1, allValues.size)
            with(allValues) {
                assertEquals("some Joke", this[0].joke)
            }

        }
    }

    @Test
    fun `given fail result from useCase, when initializing viewModel, then viewState values are set correctly`() {
        // Given
        `when`(useCase.getDadJoke()).thenReturn(
            Observable.just(
                Result.Fail(
                    "error"
                )
            )
        )

        // When
        initViewModel()

        // Then
        with(viewState) {
            verify(viewStateObserver, times((1))).onChanged(capture())
            assertEquals(1, allValues.size)
            assertEquals("", allValues[0].joke)
        }
    }

    // endregion init tests

    // region helper Functions

    private fun initViewModel() {
        underTest = DadJokesViewModel(useCase)
        underTest.viewState.observeForever(viewStateObserver)

    }
    // endregion helper Functions
}