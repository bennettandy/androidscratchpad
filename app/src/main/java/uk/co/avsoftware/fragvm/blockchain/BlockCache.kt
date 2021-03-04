package uk.co.avsoftware.fragvm.blockchain

import android.util.Log
import com.google.gson.Gson
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.CompletableEmitter
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.MaybeEmitter
import uk.co.avsoftware.fragvm.blockchain.model.Block
import uk.co.avsoftware.fragvm.blockchain.model.Cache

interface BlockCache {
    fun getBlock(): Maybe<Block>
    fun pushBlock(block: Block): Completable
}

class BlockCacheImpl(private val dao: CacheDao, private val gson: Gson) : BlockCache {

    override fun getBlock(): Maybe<Block> =
        Maybe.create { emitter: MaybeEmitter<Block> ->
            try {
                val cached = dao.findByKey(CACHE_KEY)
                    .map { cached: Cache -> gson.fromJson(cached.value, Block::class.java) }

                when (cached.isPresent) {
                    true -> emitter.onSuccess(cached.get())
                    false -> emitter.onComplete()
                }
            } catch (e: Throwable) {
                Log.e(TAG, "Error retrieving block", e)
                emitter.onError(e)
            }
        }

    override fun pushBlock(block: Block): Completable =
        Completable.create { emitter: CompletableEmitter ->
            try {
                val json = gson.toJson(block)
                Log.i(TAG, "Caching Block Json: $json")
                dao.insert(Cache(CACHE_KEY, json))
                emitter.onComplete()
            } catch (e: Throwable) {
                Log.e(TAG, "Error caching block", e)
                emitter.onError(e)
            }
        }

    companion object {
        const val TAG = "BlockCacheImpl"
        const val CACHE_KEY = "latest_block"
    }
}