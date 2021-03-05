package uk.co.avsoftware.fragvm.ui.home.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import uk.co.avsoftware.fragvm.R
import uk.co.avsoftware.fragvm.databinding.FragmentGalleryBinding
import uk.co.avsoftware.fragvm.ui.home.ui.gallery.recycler.BlockDataAdapter


class GalleryFragment() : Fragment() {

    //    private lateinit var blockChainRepository: BlockchainRepository
    private lateinit var galleryViewModel: GalleryViewModel
    private lateinit var binding: FragmentGalleryBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        galleryViewModel = ViewModelProvider(this).get(GalleryViewModel::class.java)

        // allow overriding of view model repository for testing....?
//        galleryViewModel.repository = blockChainRepository

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_gallery, container, false)
        binding.lifecycleOwner = this
        binding.viewmodel = galleryViewModel

        setUpRecyclerView()

        return binding.root
    }

    private fun setUpRecyclerView() {
        // bind RecyclerView
        val recyclerView: RecyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)

        // Set Adapter
        val adapter: BlockDataAdapter = BlockDataAdapter(emptyList())
        binding.recyclerView.adapter = adapter
        galleryViewModel.blocks.observe(viewLifecycleOwner, {
            adapter.blocks = it
            adapter.notifyDataSetChanged()
        })
    }
}