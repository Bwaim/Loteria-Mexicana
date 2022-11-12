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

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import dagger.hilt.android.AndroidEntryPoint
import dev.bwaim.loteria.app.ui.LoteriaApp
import dev.bwaim.loteria.compose.extensions.shouldUseDarkColors
import dev.bwaim.loteria.theme.ThemeActivityDelegate
import javax.inject.Inject

@ExperimentalAnimationApi
@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@AndroidEntryPoint
public class MainActivity : AppCompatActivity() {

    @Inject
    internal lateinit var themeActivityDelegate: ThemeActivityDelegate

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            LoteriaApp(
                shouldUseDarkColors = themeActivityDelegate.shouldUseDarkColors(),
                windowSizeClass = calculateWindowSizeClass(activity = this)
            )
        }
    }
}
