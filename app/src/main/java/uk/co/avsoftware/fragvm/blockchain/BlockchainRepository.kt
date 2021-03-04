package uk.co.avsoftware.fragvm.blockchain

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import uk.co.avsoftware.fragvm.blockchain.model.Block
import uk.co.avsoftware.fragvm.blockchain.model.Transaction

interface BlockchainRepository {

    fun getTransactionForHash(hash: String): Single<Transaction>

    fun getLatestBlock(): Observable<Block>
}

class BlockchainRepositoryImpl constructor(
    private val api: BlockchainDataAPI,
    private val cache: BlockCache
) :
    BlockchainRepository {

    override fun getTransactionForHash(hash: String) = api.getTransactionByHash(hash)

    override fun getLatestBlock() = cache.getBlock().toObservable()
        .mergeWith(api.getLatestBlock()
            .flatMap { cache.pushBlock(it).toSingle { it } })
}