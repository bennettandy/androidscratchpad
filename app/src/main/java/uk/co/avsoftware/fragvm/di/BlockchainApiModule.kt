package uk.co.avsoftware.fragvm.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import uk.co.avsoftware.fragvm.R
import uk.co.avsoftware.fragvm.blockchain.*
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
class BlockchainApiModule {

    @Provides
    fun provideBlockCache(dao: CacheDao, gson: Gson): BlockCache = BlockCacheImpl(dao, gson)

    @Provides
    fun provideBlockchainRepository(
        blockchainDataAPI: BlockchainDataAPI,
        cache: BlockCache
    ): BlockchainRepository =
        BlockchainRepositoryImpl(blockchainDataAPI, cache)

    @Provides
    fun provideBlockchainDataApi(retrofit: Retrofit): BlockchainDataAPI =
        retrofit.create(BlockchainDataAPI::class.java)

    @Provides
    fun provideRetrofit(
        converterFactory: GsonConverterFactory,
        @Named("baseUrl") baseUrl: String
    ): Retrofit = Retrofit.Builder()
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .baseUrl(baseUrl)
        .addConverterFactory(converterFactory)
        .build();

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideConverterFactory(gson: Gson) = GsonConverterFactory
        .create(gson)

    @Provides
    @Named("baseUrl")
    fun provideBaseUrl(@ApplicationContext appContext: Context) =
        appContext.getString(R.string.api_base_url)
}