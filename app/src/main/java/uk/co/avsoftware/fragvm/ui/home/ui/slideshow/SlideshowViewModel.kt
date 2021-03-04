package uk.co.avsoftware.fragvm.ui.home.ui.slideshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SlideshowViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is the slideshow Fragment"
    }
    val text: LiveData<String> = _text

}