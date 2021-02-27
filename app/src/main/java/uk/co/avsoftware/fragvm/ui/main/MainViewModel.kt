package uk.co.avsoftware.fragvm.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import uk.co.avsoftware.fragvm.data.LoginRepository
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val loginRepository: LoginRepository) :
    ViewModel() {

    init {
        Log.w(TAG, "Init: viewModelScope isActive: ${viewModelScope.isActive}")
    }

    private val _title: MutableLiveData<String> = MutableLiveData("bound live data value 'fred'")
    val title: LiveData<String> = _title

    private val _subtext: MutableLiveData<String> = MutableLiveData("subtext")
    val subtext: LiveData<String> = _subtext

    fun isLoggedIn(): Boolean = loginRepository.isLoggedIn

    private var job: Job? = null

    fun test() {

        job?.cancel("cancelled", Exception("duplicate instance"))

        _subtext.postValue("one two three")

        // Launch Coroutine on IO Dispatcher viewModelScope
        job = viewModelScope.launch(Dispatchers.IO) {
            Log.w(TAG, "Job Started!")
            for (i in 1..10) {
                delay(100L)
                _subtext.postValue("count $i")
                Log.w(TAG, "COUNT $i")
            }
            Log.w(TAG, "Job Finished!")
        }
    }

    /**
     * Clear down view model and close any observers etc
     */
    override fun onCleared() {
        super.onCleared()
        // check that job has been stopped by the viewModelScope clear down
        Log.w(TAG, "CLEARED ${job?.isActive}")
        Log.w(TAG, "viewModelScope isActive: ${viewModelScope.isActive}")
    }

    companion object {
        const val TAG = "MainViewModel"
    }
}