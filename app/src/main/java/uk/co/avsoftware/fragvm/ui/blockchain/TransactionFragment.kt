package uk.co.avsoftware.fragvm.ui.blockchain

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import uk.co.avsoftware.fragvm.R
import uk.co.avsoftware.fragvm.databinding.TransactionFragmentBinding

@AndroidEntryPoint
class TransactionFragment : Fragment() {

    companion object {
        fun newInstance() = TransactionFragment()
    }

    private lateinit var viewModel: TransactionViewModel

    private lateinit var binding: TransactionFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // inflate view via DataBindings
        binding = DataBindingUtil.inflate(inflater, R.layout.transaction_fragment, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // ViewModel, obtain from Hilt

        viewModel = ViewModelProvider(this).get(TransactionViewModel::class.java)

        binding.viewmodel = viewModel
    }

}