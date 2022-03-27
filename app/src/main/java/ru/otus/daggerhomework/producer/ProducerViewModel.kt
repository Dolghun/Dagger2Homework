package ru.otus.daggerhomework.producer

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import ru.otus.daggerhomework.ColorState
import java.lang.RuntimeException
import javax.inject.Inject

class ProducerViewModel @Inject constructor(
    private val colorGenerator: ColorGenerator,
    private val context: Context,
    private val colorState: MutableStateFlow<ColorState>
) : ViewModel() {

    fun onButtonClick() = generateColor()

    private fun generateColor() {
        if (context !is FragmentActivity) throw RuntimeException("Здесь нужен контекст активити")
        Toast.makeText(context, "Color sent", Toast.LENGTH_LONG).show()
        colorState.value = ColorState.Color(colorGenerator.generateColor())
    }

}
