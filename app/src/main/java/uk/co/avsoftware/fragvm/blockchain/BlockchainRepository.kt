package uk.co.avsoftware.fragvm.blockchain

import io.reactivex.Single
import uk.co.avsoftware.fragvm.blockchain.model.Transaction

interface BlockchainRepository {

    fun getTransactionForHash(hash: String): Single<Transaction>
}

class BlockchainRepositoryImpl constructor(private val api: BlockchainDataAPI) :
    BlockchainRepository {
    override fun getTransactionForHash(hash: String) = api.getTransactionByHash(hash)

}