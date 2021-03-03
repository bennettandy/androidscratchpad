package uk.co.avsoftware.fragvm.ui.home.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import uk.co.avsoftware.fragvm.blockchain.BlockchainRepository
import uk.co.avsoftware.fragvm.blockchain.CacheDao
import uk.co.avsoftware.fragvm.blockchain.model.Block
import javax.inject.Inject

@HiltViewModel

class HomeViewModel @Inject constructor(
    private val blockchainRepository: BlockchainRepository,
    private val cacheDao: CacheDao
) :
    ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is the home Fragment2"
    }
    val text: LiveData<String> = _text

    private val _latestBlock = MutableLiveData<Block>()
    val latestBlock = _latestBlock

    val hash: LiveData<String> = latestBlock.map { it.hash }
    val block_index: LiveData<Int> = latestBlock.map { it.block_index }

    private val _progress_visibility = MutableLiveData<Boolean>().apply {
        value = false
    }
    val progress_visibility: LiveData<Boolean> = _progress_visibility

    private val disposables = CompositeDisposable()

    init {
//        viewModelScope.launch(Dispatchers.IO) {
//            cacheDao.findByKey(LATEST_BLOCK_KEY).ifPresent { cache -> }
//            Log.w(TAG, "Blocks: ${blocks.size}")
//            _latestBlock.apply { postValue(blocks.last()) }
//        }
    }

    fun buttonClicked() {
        Log.w(TAG, "CLICKED")

        disposables.add(
            blockchainRepository.getLatestBlock()
                .doOnSubscribe { _progress_visibility.postValue(true) }
                .doOnTerminate { _progress_visibility.postValue(false) }
                //.doOnSuccess { cacheDao.(it) }
                .doOnSuccess(_latestBlock::postValue)
                .onErrorReturnItem(Block("?????", 0L, 0, 0))
                .subscribe()
        )

    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }

    companion object {
        const val TAG = "HomeViewModel"
        const val LATEST_BLOCK_KEY = "latest-block"
    }
}