package dev.bwaim.loteria.initializers.di

import android.content.Context
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.components.SingletonComponent
import dev.bwaim.loteria.initializers.CoilInitializer

@EntryPoint
@InstallIn(SingletonComponent::class)
internal interface InitializerEntryPoint {
    fun inject(initializer: CoilInitializer)

    companion object {
        fun resolve(context: Context): InitializerEntryPoint {
            return EntryPointAccessors.fromApplication(
                requireNotNull(context.applicationContext),
                InitializerEntryPoint::class.java
            )
        }
    }
}
