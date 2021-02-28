package uk.co.avsoftware.fragvm.exp

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class SimpleLifecycleObserver() : LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun create() {
        Log.w(TAG, "=====> CREATE")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun resume() {
        Log.w(TAG, "=====> RESUME")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun pause() {
        Log.w(TAG, "=====> PAUSE")
    }

    companion object {
        const val TAG = "SimpleLifecycleObserver"
    }
}