package uk.co.avsoftware.fragvm.blockchain

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import uk.co.avsoftware.fragvm.blockchain.model.Cache
import java.util.*

@Dao
interface CacheDao {

    @Query("SELECT * FROM cache")
    fun getAll(): List<Cache>

    @Query("SELECT * FROM cache WHERE id = :key")
    fun findByKey(key: String): Optional<Cache>

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insertLatestBlock(instance: Cache)

//    @Update(onConflict = OnConflictStrategy.IGNORE)
//    fun updateLatestBlock(instance: Cache)

//    @Insert
//    fun insertAll(vararg caches: Cache)

    @Delete
    fun delete(cache: Cache)

}