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

package dev.bwaim.loteria.app.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dev.bwaim.loteria.app.navigation.LoteriaNavHost
import dev.bwaim.loteria.app.navigation.LoteriaTopLevelNavigation
import dev.bwaim.loteria.compose.component.LoteriaBackground
import dev.bwaim.loteria.compose.theme.LoteriaTheme

@OptIn(ExperimentalAnimationApi::class, ExperimentalLayoutApi::class)
@Composable
internal fun LoteriaApp(
    shouldUseDarkColors: Boolean,
    windowSizeClass: WindowSizeClass,
) {
    val systemUiController = rememberSystemUiController()

    LoteriaTheme(darkTheme = shouldUseDarkColors) {
        // Update the dark content of the system bars to match the theme
        val colorStatusBar = MaterialTheme.colorScheme.background
        DisposableEffect(systemUiController, shouldUseDarkColors) {
            systemUiController.systemBarsDarkContentEnabled = !shouldUseDarkColors
            systemUiController.setStatusBarColor(
                color = colorStatusBar,
                darkIcons = !shouldUseDarkColors,
            )
            onDispose {}
        }

        val navController = rememberAnimatedNavController()
        val loteriaTopLevelNavigation = remember(navController) {
            LoteriaTopLevelNavigation(navController)
        }

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        LoteriaBackground {
            Scaffold(
                modifier = Modifier,
                containerColor = Color.Transparent,
                contentColor = MaterialTheme.colorScheme.onBackground,
            ) { padding ->
                LoteriaNavHost(
                    navController = navController,
                    modifier = Modifier
                        .padding(padding)
                        .consumeWindowInsets(padding),
                )
            }
        }
    }
}
