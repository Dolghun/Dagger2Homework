package ru.otus.daggerhomework

import android.content.Context
import javax.inject.Named

interface MainActivityDependencies {
    fun appContext(): Context
}