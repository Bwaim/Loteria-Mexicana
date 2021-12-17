package dev.bwaim.loteria.theme.impl

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.bwaim.loteria.theme.ThemeRepository

@Module
@InstallIn(SingletonComponent::class)
internal interface ThemeRepositoryModule {
    @get:Binds
    val ThemeRepositoryImpl.bindThemeRepository: ThemeRepository
}
