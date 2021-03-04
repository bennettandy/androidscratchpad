package uk.co.avsoftware.fragvm.ui.home.ui.gallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uk.co.avsoftware.fragvm.blockchain.model.Block
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor() : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is the gallery Fragment, will hold recyclerview"
    }
    val text: LiveData<String> = _text

    private val _blocks = MutableLiveData<List<Block>>().apply {
        value = listOf(
            Block("aadsdsdsad", 1000L, 34, 3434),
            Block("gfshfghgdfhgfhgdh", 2001, 1334, 9235)
        )
    }
    val blocks: LiveData<List<Block>> = _blocks
}