package ru.otus.daggerhomework.receiver

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Component
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.flow.MutableStateFlow
import ru.otus.daggerhomework.ColorState
import ru.otus.daggerhomework.FragmentScope
import ru.otus.daggerhomework.MainActivityComponent
import javax.inject.Named

@FragmentScope
@Component(
    dependencies = [MainActivityComponent::class],
    modules = [FragmentReceiverModule::class]
)
interface FragmentReceiverComponent {

    fun inject(fragmentReceiver: FragmentReceiver)
}

@Module
class FragmentReceiverModule {

    @Provides
    fun provideReceiverViewModelFactory(
        @Named("app") context: Context,
        eventHandler: MutableStateFlow<ColorState>
    ): ViewModelProvider.Factory {
        return object : ViewModelProvider.Factory {

            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                require(modelClass == ReceiverViewModel::class.java)
                return ReceiverViewModel(context, eventHandler) as T
            }
        }
    }
}
