package uk.co.avsoftware.fragvm.ui.main

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import uk.co.avsoftware.fragvm.R
import uk.co.avsoftware.fragvm.databinding.MainFragmentBinding
import uk.co.avsoftware.fragvm.ui.login.LoginActivity

@AndroidEntryPoint
class MainFragment : Fragment() {

    private lateinit var viewModel: MainViewModel

    private lateinit var binding: MainFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // inflate view via DataBindings
        binding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        Log.i(TAG, "OnActivityCreated()")
        // ViewModel, obtain or create new from factory
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        binding.viewmodel = viewModel

        when (viewModel.isLoggedIn()) {

            true -> Toast.makeText(context, "XXX Logged In", Toast.LENGTH_LONG).show()

            false -> Intent(context, LoginActivity::class.java).also {
                startActivity(it)
            }

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.i(TAG, "On View Created")

    }

    companion object {
        fun newInstance() = MainFragment()
        const val TAG = "MainFragment"
    }
}