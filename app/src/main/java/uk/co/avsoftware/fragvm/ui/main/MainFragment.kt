package uk.co.avsoftware.fragvm.ui.main

import android.animation.Animator
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.AnticipateInterpolator
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
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
        binding.lifecycleOwner = this
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
                startActivityForResult(it, REQUEST_CODE)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            REQUEST_CODE -> animate()
        }
    }

    private fun animate() {
        ObjectAnimator.ofFloat(binding.boxer, "translationX", 1000f).apply {
            duration = 1000
            startDelay = 1000
            interpolator = AnticipateInterpolator(4.0f)
            start()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.i(TAG, "On View Created")

    }

    companion object {
        fun newInstance() = MainFragment()
        const val TAG = "MainFragment"
        const val REQUEST_CODE = 100
    }
}