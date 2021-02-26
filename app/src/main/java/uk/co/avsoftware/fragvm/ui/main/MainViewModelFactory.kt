package uk.co.avsoftware.fragvm.ui.main;

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import uk.co.avsoftware.fragvm.data.LoginDataSource
import uk.co.avsoftware.fragvm.data.LoginRepository

/**
 * ViewModel provider factory to instantiate LoginViewModel.
 * Required given LoginViewModel has a non-empty constructor
 */
class MainViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        Log.i(TAG, "CREATE MainViewModel")

        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(
                loginRepository = LoginRepository(
                    dataSource = LoginDataSource()
                )
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

    companion object {
        const val TAG = "LoginViewModelFactory"
    }
}