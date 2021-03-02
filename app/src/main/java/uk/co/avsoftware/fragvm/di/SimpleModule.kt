package uk.co.avsoftware.fragvm.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import uk.co.avsoftware.fragvm.exp.SimpleLifecycleObserver

@Module
@InstallIn(ActivityComponent::class)
object SimpleModule {

    @Provides
    fun providesSimpleLifecycleObserver(): SimpleLifecycleObserver = SimpleLifecycleObserver()
}