package ru.otus.daggerhomework

import dagger.Module
import dagger.Provides
import kotlinx.coroutines.flow.MutableStateFlow

@Module
object MainActivityModule {
    @ActivityScope
    @Provides
    fun providesMutableColorState(): MutableStateFlow<ColorState> =
        MutableStateFlow(ColorState.Init)
}