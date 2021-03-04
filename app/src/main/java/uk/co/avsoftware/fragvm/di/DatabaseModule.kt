package uk.co.avsoftware.fragvm.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import uk.co.avsoftware.fragvm.blockchain.AppDatabase
import uk.co.avsoftware.fragvm.blockchain.BlockDao
import uk.co.avsoftware.fragvm.blockchain.CacheDao

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideDatabase(@ApplicationContext applicationContext: Context): AppDatabase =
        Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "blockchain-database"
        ).build()

    @Provides
    fun provideBlockDao(database: AppDatabase): BlockDao = database.blockDao()

    @Provides
    fun provideCacheDao(database: AppDatabase): CacheDao = database.cacheDao()
}