package uk.co.avsoftware.fragvm.blockchain

import androidx.room.*
import uk.co.avsoftware.fragvm.blockchain.model.Cache
import java.util.*

@Dao
interface CacheDao {

    @Query("SELECT * FROM cache")
    fun getAll(): List<Cache>

    @Query("SELECT * FROM cache WHERE id = :key")
    fun findByKey(key: String): Optional<Cache>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(cache: Cache)

    @Delete
    fun delete(cache: Cache)

}