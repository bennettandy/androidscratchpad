package uk.co.avsoftware.fragvm.blockchain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cache")
data class Cache(
    @PrimaryKey val id: String,
    val value: String
)