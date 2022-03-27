package ru.otus.daggerhomework.receiver

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorInt
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.otus.daggerhomework.DependenciesProvider
import ru.otus.daggerhomework.MainActivity
import ru.otus.daggerhomework.R
import javax.inject.Inject

class FragmentReceiver : Fragment(R.layout.fragment_b) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: ReceiverViewModel by viewModels { viewModelFactory }

    private var frame: View? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerFragmentReceiverComponent.builder()
            .mainActivityComponent((requireActivity() as MainActivity).activityComponent)
            .build()
            .inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        frame = view.findViewById(R.id.frame)
        observeColors()
    }

    private fun observeColors() = with(viewLifecycleOwner) {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.colors.collect { state ->
                    populateColor(state.color)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        frame = null
    }

    private fun populateColor(@ColorInt color: Int) {
        frame?.setBackgroundColor(color)
    }
}