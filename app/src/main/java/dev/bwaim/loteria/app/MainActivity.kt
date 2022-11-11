/*
 * Copyright 2022 Dev Bwaim team
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.bwaim.loteria.app

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.core.os.LocaleListCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.components.SingletonComponent
import dev.bwaim.loteria.app.ui.LoteriaApp
import dev.bwaim.loteria.compose.extensions.shouldUseDarkColors
import dev.bwaim.loteria.core.utils.LocaleManager
import dev.bwaim.loteria.locale.LocaleService
import dev.bwaim.loteria.locale.impl.LocaleActivityDelegateImpl
import dev.bwaim.loteria.theme.ThemeActivityDelegate
import java.util.Locale
import javax.inject.Inject
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking

@ExperimentalAnimationApi
@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@AndroidEntryPoint
public class MainActivity : ComponentActivity() {

    @Inject
    internal lateinit var themeActivityDelegate: ThemeActivityDelegate

    @Inject
    internal lateinit var localeActivityDelegate: LocaleActivityDelegateImpl

    private lateinit var entryPoint: MainActivityEntryPoint

    override fun attachBaseContext(newBase: Context) {
        entryPoint = EntryPointAccessors.fromApplication(
            newBase,
            MainActivityEntryPoint::class.java
        )
        val locale = runBlocking { entryPoint.localeService.getLocale() }.run {
            if (language.equals("")) {
                Locale.getDefault()
            } else {
                this
            }
        }
        applyOverrideConfiguration(LocaleManager.getLocalizedConfiguration(locale))
        super.attachBaseContext(newBase)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        initializeAppLocales()

        WindowCompat.setDecorFitsSystemWindows(window, false)

        localeActivityDelegate.locale
            .flowWithLifecycle(lifecycle)
            .drop(1)
            .onEach {
                LocaleManager.applyLocalizedContext(this, it)
                recreate()
            }
            .launchIn(lifecycleScope)

        setContent {
            LoteriaApp(
                shouldUseDarkColors = themeActivityDelegate.shouldUseDarkColors(),
                windowSizeClass = calculateWindowSizeClass(activity = this)
            )
        }
    }

    private fun initializeAppLocales() {
        val defaultLocales = LocaleListCompat.create(
            Locale("es"),
            Locale.ENGLISH,
            Locale.FRENCH
        )

        AppCompatDelegate.setApplicationLocales(defaultLocales)
    }

    @EntryPoint
    @InstallIn(SingletonComponent::class)
    internal interface MainActivityEntryPoint {
        val localeService: LocaleService
    }
}
