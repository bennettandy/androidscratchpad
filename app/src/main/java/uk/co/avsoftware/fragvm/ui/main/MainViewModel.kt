package uk.co.avsoftware.fragvm.ui.main

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import uk.co.avsoftware.fragvm.data.LoginRepository

class MainViewModel @ViewModelInject constructor(private val loginRepository: LoginRepository) :
    ViewModel() {

    private val _title: MutableLiveData<String> = MutableLiveData("bound live data value 'fred'")
    val title: LiveData<String> = _title

    private val _subtext: MutableLiveData<String> = MutableLiveData("subtext")
    val subtext: LiveData<String> = _subtext

    fun isLoggedIn(): Boolean = loginRepository.isLoggedIn

    fun test() {

        _subtext.postValue("one two three")

        // Launch Coroutine on IO Dispatcher viewModelScope
        val s: Job = viewModelScope.launch(Dispatchers.IO) {
            for (i in 1..1000) {
                delay(100L)
                _subtext.postValue("count $i")
                Log.w("MainViewModel", "COUNT $i")
            }
        }
    }

    /**
     * Clear down view model and close any observers etc
     */
    override fun onCleared() {
        super.onCleared()
        Log.w(TAG, "CLEARED")
    }

    companion object {
        const val TAG = "MainViewModel"
    }
}