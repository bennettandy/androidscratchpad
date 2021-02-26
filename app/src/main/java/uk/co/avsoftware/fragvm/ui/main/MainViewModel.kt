package uk.co.avsoftware.fragvm.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uk.co.avsoftware.fragvm.data.LoginRepository

class MainViewModel @ViewModelInject constructor(private val loginRepository: LoginRepository) :
    ViewModel() {

    private val _title: MutableLiveData<String> = MutableLiveData("bound live data value 'fred'")
    val title: LiveData<String> = _title

    fun isLoggedIn(): Boolean = loginRepository.isLoggedIn

}