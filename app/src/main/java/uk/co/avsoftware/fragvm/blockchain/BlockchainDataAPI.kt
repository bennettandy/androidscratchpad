package uk.co.avsoftware.fragvm.blockchain

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import uk.co.avsoftware.fragvm.blockchain.model.Block
import uk.co.avsoftware.fragvm.blockchain.model.Transaction

/**
 * https://rapidapi.com/BraveNewCoin/api/bravenewcoin
 */
interface BlockchainDataAPI {

    @GET("https://blockchain.info/rawtx/{tx_hash}")
    fun getTransactionByHash(@Path("tx_hash") tx_hash: String): Single<Transaction>

    @GET("https://blockchain.info/latestblock")
    fun getLatestBlock(): Single<Block>
}