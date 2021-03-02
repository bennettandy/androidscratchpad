package uk.co.avsoftware.fragvm.ui.home.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import uk.co.avsoftware.fragvm.blockchain.model.Block
import java.util.*

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is the home Fragment2"
    }
    val text: LiveData<String> = _text

    private val _latestBlock = MutableLiveData<Block>()
    val latestBlock = _latestBlock

    val hash: LiveData<String> = latestBlock.map { it.hash }
    val block_index: LiveData<Int> = latestBlock.map { it.block_index }

    fun buttonClicked() {
        Log.w(TAG, "CLICKED")
        _latestBlock.postValue(
            Block(
                UUID.randomUUID().toString(),
                45666L,
                10,
                30,
                listOf(234L, 45667L)
            )
        )
    }

    companion object {
        const val TAG = "HomeViewModel"
    }
}