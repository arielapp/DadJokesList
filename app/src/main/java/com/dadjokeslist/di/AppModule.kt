package com.dadjokeslist.di

import androidx.lifecycle.ViewModel
import com.dadjokeslist.jokes.api.JokesApi
import com.dadjokeslist.jokes.repository.remote.JokesRemoteRepository
import com.dadjokeslist.jokes.repository.remote.JokesRemoteRepositoryImpl
import com.dadjokeslist.jokes.usecase.DadJokesUseCase
import com.dadjokeslist.jokes.usecase.DadJokesUseCaseImpl
import com.dadjokeslist.jokes.viewmodel.DadJokesViewModel
import com.dadjokeslist.util.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Provider


@Module
object AppModule {

    @Provides
    fun provideJokesApi(): JokesApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(JokesApi::class.java)

    @Provides
    fun provideViewModelFactory(viewModels: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>): ViewModelFactory {
        return ViewModelFactory.create(viewModels)
    }

    @Provides
    fun provideJokesRepository(api: JokesApi): JokesRemoteRepository = JokesRemoteRepositoryImpl(api)

    @Provides
    fun provideDadJokesUseCase(repo: JokesRemoteRepository): DadJokesUseCase = DadJokesUseCaseImpl(repo)

    @Provides
    @IntoMap
    @ViewModelKey(DadJokesViewModel::class)
    fun provideEmployeeListViewModel(useCase: DadJokesUseCase): ViewModel {
        return DadJokesViewModel(useCase)
    }
}