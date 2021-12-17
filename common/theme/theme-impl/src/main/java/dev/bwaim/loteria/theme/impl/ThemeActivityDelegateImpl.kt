package dev.bwaim.loteria.theme.impl

import androidx.annotation.VisibleForTesting
import dev.bwaim.loteria.coroutines.ApplicationScope
import dev.bwaim.loteria.theme.Theme
import dev.bwaim.loteria.theme.ThemeActivityDelegate
import dev.bwaim.loteria.theme.ThemeService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.runBlocking
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@VisibleForTesting
public class ThemeActivityDelegateImpl @Inject constructor(
    @ApplicationScope applicationScope: CoroutineScope,
    themeService: ThemeService
) : ThemeActivityDelegate {
    override val theme: StateFlow<Theme> = themeService
        .observeTheme()
        .stateIn(
            applicationScope,
            SharingStarted.Eagerly,
            runBlocking { themeService.observeTheme().first() }
        )
}
