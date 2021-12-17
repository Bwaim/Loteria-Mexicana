package dev.bwaim.loteria.theme.impl

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.bwaim.loteria.theme.ThemeActivityDelegate

@Module
@InstallIn(SingletonComponent::class)
internal interface ThemeActivityDelegateModule {
    @get:Binds
    val ThemeActivityDelegateImpl.bindThemeActivityDelegate: ThemeActivityDelegate
}
