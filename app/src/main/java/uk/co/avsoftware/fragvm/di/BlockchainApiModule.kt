package uk.co.avsoftware.fragvm.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import uk.co.avsoftware.fragvm.R
import uk.co.avsoftware.fragvm.blockchain.BlockchainDataAPI
import uk.co.avsoftware.fragvm.blockchain.BlockchainRepository
import uk.co.avsoftware.fragvm.blockchain.BlockchainRepositoryImpl
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
class BlockchainApiModule {

    @Provides
    fun provideBlockchainRepository(blockchainDataAPI: BlockchainDataAPI): BlockchainRepository =
        BlockchainRepositoryImpl(blockchainDataAPI)

    @Provides
    fun provideBlockchainDataApi(retrofit: Retrofit): BlockchainDataAPI =
        retrofit.create(BlockchainDataAPI::class.java)

    @Provides
    fun provideRetrofit(
        converterFactory: GsonConverterFactory,
        @Named("baseUrl") baseUrl: String
    ): Retrofit = Retrofit.Builder()
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .baseUrl(baseUrl)
        .addConverterFactory(converterFactory)
        .build();

    @Provides
    fun provideConverterFactory() = GsonConverterFactory.create()

    @Provides
    @Named("baseUrl")
    fun provideBaseUrl(@ApplicationContext appContext: Context) =
        appContext.getString(R.string.api_base_url)
}