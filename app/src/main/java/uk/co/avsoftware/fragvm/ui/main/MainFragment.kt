package uk.co.avsoftware.fragvm.ui.main

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uk.co.avsoftware.fragvm.R
import uk.co.avsoftware.fragvm.ui.login.LoginActivity

class MainFragment : Fragment() {

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // ViewModel, obtain or create new from factory
        viewModel = ViewModelProvider(this, MainViewModelFactory()).get(MainViewModel::class.java)

        // TODO: Use the ViewModel
        Log.i(TAG, "OnActivityCreated()")

        if (!viewModel.isLoggedIn()) {
            Log.i(TAG, "Not Logged In")
            startActivity(Intent(context, LoginActivity::class.java))
        }
    }


    companion object {
        fun newInstance() = MainFragment()

        const val TAG = "MainFragment"
    }
}