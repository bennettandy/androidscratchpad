package uk.co.avsoftware.fragvm.blockchain

import androidx.room.Database
import androidx.room.RoomDatabase
import uk.co.avsoftware.fragvm.blockchain.model.Block

@Database(entities = arrayOf(Block::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun blockDao(): BlockDao
}