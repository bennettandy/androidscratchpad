package uk.co.avsoftware.fragvm.blockchain

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import uk.co.avsoftware.fragvm.blockchain.model.Block

@Dao
interface BlockDao {
    @Query("SELECT * FROM blocks")
    fun getAll(): List<Block>

//    @Query("SELECT * FROM block WHERE hash IN (:hashes)")
//    fun loadAllByIds(hashes: Array<String>): List<String>
//
//    @Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
//            "last_name LIKE :last LIMIT 1")
//    fun findByName(first: String, last: String): Block

    @Insert
    fun insertAll(vararg blocks: Block)

    @Delete
    fun delete(block: Block)
}