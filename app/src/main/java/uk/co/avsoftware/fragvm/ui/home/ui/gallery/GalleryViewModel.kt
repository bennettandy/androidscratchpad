package uk.co.avsoftware.fragvm.ui.home.ui.gallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
// do we need @Inject constructor for hild
class GalleryViewModel @Inject constructor() : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is the gallery Fragment, will hold recyclerview"
    }
    val text: LiveData<String> = _text
}