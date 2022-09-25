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

package dev.bwaim.loteria.app.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import dev.bwaim.loteria.app.mainmenu.navigation.MainMenuDestination
import dev.bwaim.loteria.app.mainmenu.navigation.mainMenuGraph
import dev.bwaim.loteria.draw.navigation.DrawDestination
import dev.bwaim.loteria.draw.navigation.drawGraph
import dev.bwaim.loteria.settings.navigation.SettingsDestination
import dev.bwaim.loteria.settings.navigation.settingsGraph

@OptIn(ExperimentalAnimationApi::class)
@Composable
public fun LoteriaNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = MainMenuDestination.route
) {
    AnimatedNavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        mainMenuGraph(
            navigateToSettings = { navController.navigate(SettingsDestination.route) },
            navigateToDraw = { navController.navigate(DrawDestination.route) },
            nestedGraphs = {
                settingsGraph(onBackClick = { navController.popBackStack() })
                drawGraph(onBackClick = { navController.popBackStack() })
            }
        )
    }
}
