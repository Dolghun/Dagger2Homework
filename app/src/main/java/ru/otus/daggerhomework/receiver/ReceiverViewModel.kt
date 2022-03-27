package ru.otus.daggerhomework.receiver

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.*
import ru.otus.daggerhomework.ColorState
import javax.inject.Inject

class ReceiverViewModel @Inject constructor(
    private val context: Context,
    val colorState: StateFlow<ColorState>
) : ViewModel() {

    val colors: Flow<ColorState.Color> = colorState
        .onStart {
            if (context !is Application) throw RuntimeException("Здесь нужен контекст апликейшена")
        }
        .onEach {
            Toast.makeText(context, "Color received", Toast.LENGTH_LONG).show()
        }
        .filterIsInstance()

}
