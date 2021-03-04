package uk.co.avsoftware.fragvm.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.*
import uk.co.avsoftware.fragvm.blockchain.BlockchainRepository
import uk.co.avsoftware.fragvm.blockchain.model.Transaction
import uk.co.avsoftware.fragvm.data.LoginRepository
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val loginRepository: LoginRepository,
    private val blockchainRepository: BlockchainRepository
) :
    ViewModel() {

    init {
        Log.w(TAG, "Init: viewModelScope isActive: ${viewModelScope.isActive}")
    }

    private val disposables = CompositeDisposable()

    private val _title: MutableLiveData<String> = MutableLiveData("bound live data value 'fred'")
    val title: LiveData<String> = _title

    private val _subtext: MutableLiveData<String> = MutableLiveData("subtext")
    val subtext: LiveData<String> = _subtext

    // Blockchain Transaction
    private val _selectedTransaction: MutableLiveData<Transaction> = MutableLiveData()
    val transaction: LiveData<Transaction> = _selectedTransaction

    fun isLoggedIn(): Boolean = loginRepository.isLoggedIn

    // handle to possible running coroutine Job
    private var job: Job? = null

    fun test() {

        disposables.add(
            blockchainRepository.getTransactionForHash("dbb5f929379d6ae8a055335220c4cdf4c735aa12d5e0c1102f7b466bc8aa570e")
                .subscribeOn(Schedulers.io())
                .doOnSuccess(_selectedTransaction::postValue)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    // named arguments for lambda Subscribers
                    onSuccess = { Log.w(TAG, "Done Transaction: $it") },
                    onError = { Log.e(TAG, "Failed", it) },
                )
        )

        // cancel any already running Job so we don't create more than one
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

        disposables.dispose()

        // check that job has been stopped by the viewModelScope clear down
        Log.w(TAG, "CLEARED ${job?.isActive}")
        Log.w(TAG, "viewModelScope isActive: ${viewModelScope.isActive}")
    }

    companion object {
        const val TAG = "MainViewModel"
    }
}