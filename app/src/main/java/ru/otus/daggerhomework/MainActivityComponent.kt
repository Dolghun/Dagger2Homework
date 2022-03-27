package ru.otus.daggerhomework

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.otus.daggerhomework.app.ApplicationComponent
import javax.inject.Named

const val NAME_ACTIVITY_CONTEXT = "activityContext"

@ActivityScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [MainActivityModule::class]
)
interface MainActivityComponent {

    @Named("app")
    fun provideAppContext(): Context

    @Named("act")
    fun provideActivityContext(): Context

    fun provideEventHandler(): MutableStateFlow<ColorState>

    @Component.Factory
    interface Factory {

        fun create(
            appComponent: ApplicationComponent,
            @BindsInstance @Named("act") context: Context
        ): MainActivityComponent
    }
}
