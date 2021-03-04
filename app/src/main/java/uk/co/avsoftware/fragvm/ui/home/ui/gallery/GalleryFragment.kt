package uk.co.avsoftware.fragvm.ui.home.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import uk.co.avsoftware.fragvm.R
import uk.co.avsoftware.fragvm.databinding.FragmentGalleryBinding

@AndroidEntryPoint
class GalleryFragment : Fragment() {

    private lateinit var galleryViewModel: GalleryViewModel
    private lateinit var binding: FragmentGalleryBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        galleryViewModel =
            ViewModelProvider(this).get(GalleryViewModel::class.java)

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_gallery, container, false)

        binding.viewmodel = galleryViewModel

        binding.lifecycleOwner = this

        return binding.root
    }
}