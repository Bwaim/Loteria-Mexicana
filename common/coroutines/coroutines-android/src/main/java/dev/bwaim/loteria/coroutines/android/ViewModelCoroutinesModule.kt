package dev.bwaim.loteria.coroutines.android

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dev.bwaim.loteria.coroutines.MainDispatcher
import dev.bwaim.loteria.coroutines.ViewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

@Module
@InstallIn(ViewModelComponent::class)
internal object ViewModelCoroutinesModule {
    @Provides
    @ViewModelScoped
    @ViewModelScope
    fun provideViewModelScope(
        @MainDispatcher mainDispatcher: CoroutineDispatcher
    ) = CoroutineScope(SupervisorJob() + mainDispatcher)
}
