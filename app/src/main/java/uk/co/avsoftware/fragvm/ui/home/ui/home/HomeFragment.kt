package uk.co.avsoftware.fragvm.ui.home.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import dagger.hilt.android.AndroidEntryPoint
import uk.co.avsoftware.fragvm.R
import uk.co.avsoftware.fragvm.databinding.FragmentHomeBinding

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        // bind fragment lifecycle to view binding object
        binding.lifecycleOwner = this

        // set the view model onto the view binding
        binding.viewmodel = homeViewModel

        binding.navToGallery.setOnClickListener {
            it.findNavController()
                .navigate(HomeFragmentDirections.actionNavHomeToNavGallery(homeViewModel.hash.value))
        }

        binding.navToSlideshow.setOnClickListener {
            it.findNavController().navigate(HomeFragmentDirections.actionNavHomeToNavSlideshow())
        }

        return binding.root
    }

    companion object {
        const val TAG = "HomeFragment"
    }
}