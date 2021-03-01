package uk.co.avsoftware.fragvm.ui.blockchain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uk.co.avsoftware.fragvm.blockchain.BlockchainRepository
import javax.inject.Inject

@HiltViewModel
class TransactionViewModel @Inject constructor(private val blockchainRepository: BlockchainRepository) :
    ViewModel() {
    // TODO: Implement the ViewModel

    private val _heading: MutableLiveData<String> = MutableLiveData()
    val heading: LiveData<String> = _heading
}