package uk.co.avsoftware.fragvm.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import uk.co.avsoftware.fragvm.data.LoginRepository

class MainViewModel @ViewModelInject constructor(private val loginRepository: LoginRepository) :
    ViewModel() {

    fun isLoggedIn(): Boolean = loginRepository.isLoggedIn
}