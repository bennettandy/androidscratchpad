package uk.co.avsoftware.fragvm.ui.home.ui.gallery.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import uk.co.avsoftware.fragvm.R
import uk.co.avsoftware.fragvm.blockchain.model.Block
import uk.co.avsoftware.fragvm.databinding.BlockListItemBinding

class BlockDataAdapter(var blocks: List<Block> = emptyList()) :
    RecyclerView.Adapter<BlockDataAdapter.BlockViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlockViewHolder {
        val binding: BlockListItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.block_list_item,
            parent,
            false
        )
        return BlockViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BlockViewHolder, position: Int) {
        // bind position
        holder.binding.block = blocks[position]
    }

    override fun getItemCount(): Int = blocks.size

    class BlockViewHolder(val binding: BlockListItemBinding) : RecyclerView.ViewHolder(binding.root)
}