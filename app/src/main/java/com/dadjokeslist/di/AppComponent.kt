package com.dadjokeslist.di

import com.dadjokeslist.jokes.view.MainActivity
import dagger.Component

@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(activity: MainActivity)
}