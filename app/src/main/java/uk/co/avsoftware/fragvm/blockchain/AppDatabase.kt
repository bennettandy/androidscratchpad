package uk.co.avsoftware.fragvm.blockchain

import androidx.room.Database
import androidx.room.RoomDatabase
import uk.co.avsoftware.fragvm.blockchain.model.Block
import uk.co.avsoftware.fragvm.blockchain.model.Cache

@Database(entities = arrayOf(Block::class, Cache::class), version = 3)
abstract class AppDatabase : RoomDatabase() {
    abstract fun blockDao(): BlockDao
    abstract fun cacheDao(): CacheDao
}