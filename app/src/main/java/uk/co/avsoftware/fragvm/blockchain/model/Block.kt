package uk.co.avsoftware.fragvm.blockchain.model

data class Block(
    val hash: String,
    val time: Long,
    val block_index: Int,
    val height: Int,
    val tx_indexes: List<Long>
)