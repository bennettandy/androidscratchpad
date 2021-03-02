package uk.co.avsoftware.fragvm.ui.home.ui.home

import android.util.Log
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uk.co.avsoftware.fragvm.blockchain.BlockDao
import uk.co.avsoftware.fragvm.blockchain.BlockchainRepository
import uk.co.avsoftware.fragvm.blockchain.model.Block
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val blockchainRepository: BlockchainRepository,
    private val blockDao: BlockDao
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
        viewModelScope.launch(Dispatchers.IO) {
            val blocks: List<Block> = blockDao.getAll()
            Log.w(TAG, "Blocks: ${blocks.size}")
            _latestBlock.apply { postValue(blocks.last()) }
        }
    }

    fun buttonClicked() {
        Log.w(TAG, "CLICKED")

        disposables.add(
            blockchainRepository.getLatestBlock()
                .doOnSubscribe { _progress_visibility.postValue(true) }
                .doOnTerminate { _progress_visibility.postValue(false) }
                .doOnSuccess { blockDao.insertAll(it) }
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
    }
}