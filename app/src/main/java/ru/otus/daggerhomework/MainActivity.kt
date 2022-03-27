package ru.otus.daggerhomework

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.otus.daggerhomework.producer.FragmentProducer
import ru.otus.daggerhomework.receiver.FragmentReceiver

class MainActivity : AppCompatActivity(), DependenciesProvider<MainActivityComponent> {

    lateinit var activityComponent: MainActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        activityComponent = DaggerMainActivityComponent.factory().create(appComponent, this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fReceiver, FragmentReceiver())
                .replace(R.id.fProducer, FragmentProducer())
                .commit()
        }
    }

    override fun getDependencies(): MainActivityComponent = activityComponent
}