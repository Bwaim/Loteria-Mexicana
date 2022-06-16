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

package dev.bwaim.loteria.app.mainmenu.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.navigation
import dev.bwaim.loteria.app.mainmenu.MainMenuRoute
import dev.bwaim.loteria.navigation.LoteriaNavigationDestination

object MainMenuDestination : LoteriaNavigationDestination {
    override val route = "main_menu_route"
    override val destination = "main_menu_destination"
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.mainMenuGraph(
    navigateToSettings: () -> Unit,
    navigateToDraw: () -> Unit,
    nestedGraphs: NavGraphBuilder.() -> Unit
) {
    navigation(
        route = MainMenuDestination.route,
        startDestination = MainMenuDestination.destination
    ) {
        composable(route = MainMenuDestination.destination) {
            MainMenuRoute(
                navigateToSettings = navigateToSettings,
                navigateToDraw = navigateToDraw
            )
        }
        nestedGraphs()
    }
}
