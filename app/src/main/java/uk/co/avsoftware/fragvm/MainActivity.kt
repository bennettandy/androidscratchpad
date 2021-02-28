package uk.co.avsoftware.fragvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import dagger.hilt.android.AndroidEntryPoint
import uk.co.avsoftware.fragvm.exp.SimpleLifecycleObserver
import uk.co.avsoftware.fragvm.ui.main.MainFragment
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var observer: SimpleLifecycleObserver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycle.addObserver(observer)

        Log.w(TAG, "Observer $observer")

        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }

    companion object {
        const val TAG = "MainActivity"
    }
}