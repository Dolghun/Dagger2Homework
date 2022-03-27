package ru.otus.daggerhomework.producer

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import ru.otus.daggerhomework.DependenciesProvider
import ru.otus.daggerhomework.MainActivity
import ru.otus.daggerhomework.R
import javax.inject.Inject


class FragmentProducer : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: ProducerViewModel by viewModels { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val dependenciesProvider = activity as? DependenciesProvider<*>
            ?: throw ClassCastException(
                "Activity must implement `DependenciesProvider` of `FragmentProducerDependencies`"
            )
        DaggerFragmentProducerComponent.builder()
            .mainActivityComponent((requireActivity() as MainActivity).activityComponent)
            .build()
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_a, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.button).setOnClickListener {
            viewModel.onButtonClick()
        }
    }
}
