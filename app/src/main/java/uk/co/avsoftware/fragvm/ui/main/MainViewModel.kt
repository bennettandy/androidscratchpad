package uk.co.avsoftware.fragvm.ui.main

import androidx.lifecycle.ViewModel
import uk.co.avsoftware.fragvm.data.LoginRepository

class MainViewModel(private val loginRepository: LoginRepository) : ViewModel() {

    fun isLoggedIn(): Boolean = loginRepository.isLoggedIn
}