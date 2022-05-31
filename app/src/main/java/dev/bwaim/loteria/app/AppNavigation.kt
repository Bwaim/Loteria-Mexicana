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

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.navigation
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dev.bwaim.loteria.draw.Draw
import dev.bwaim.loteria.settings.Settings

internal sealed class Screen(val route: String) {
    object MainMenu : Screen("mainMenu")
    object Settings : Screen("settings")
    object Draw : Screen("draw")
}

private sealed class LeafScreen(
    private val route: String,
) {
    fun createRoute(root: Screen) = "${root.route}/$route"

    object MainMenu : LeafScreen("mainMenu")
    object Settings : LeafScreen("settings")
    object Draw : LeafScreen("draw")
}

// TODO: remove the transition or change it when https://github.com/google/accompanist/issues/773
//  is solved
@ExperimentalAnimationApi
@Composable
internal fun AppNavigation() {
    val navController = rememberAnimatedNavController()
    AnimatedNavHost(
        navController,
        startDestination = Screen.MainMenu.route
    ) {
        addMainMenuTopLevel(navController)
        addSettingsTopLevel(navController)
        addDrawTopLevel(navController)
    }
}

@ExperimentalAnimationApi
private fun NavGraphBuilder.addMainMenuTopLevel(
    navController: NavController
) {
    navigation(
        route = Screen.MainMenu.route,
        startDestination = LeafScreen.MainMenu.createRoute(Screen.MainMenu)
    ) {
        addMainMenu(navController, Screen.MainMenu)
    }
}

@ExperimentalAnimationApi
private fun NavGraphBuilder.addMainMenu(
    navController: NavController,
    root: Screen
) {
    composable(LeafScreen.MainMenu.createRoute(root)) {
        MainMenu(
            openSettings = {
                navController.navigate(Screen.Settings.route)
            },
            openDraw = {
                /* Opens the game screen */
                navController.navigate(Screen.Draw.route)
            }
        )
    }
}

@ExperimentalAnimationApi
private fun NavGraphBuilder.addSettingsTopLevel(
    navController: NavController
) {
    navigation(
        route = Screen.Settings.route,
        startDestination = LeafScreen.Settings.createRoute(Screen.Settings)
    ) {
        addSettings(navController, Screen.Settings)
    }
}

@ExperimentalAnimationApi
private fun NavGraphBuilder.addSettings(
    navController: NavController,
    root: Screen
) {
    composable(LeafScreen.Settings.createRoute(root)) {
        Settings(
            navigateUp = { navController.navigateUp() }
        )
    }
}

@ExperimentalAnimationApi
private fun NavGraphBuilder.addDrawTopLevel(
    navController: NavController
) {
    navigation(
        route = Screen.Draw.route,
        startDestination = LeafScreen.Draw.createRoute(Screen.Draw)
    ) {
        addDraw(navController, Screen.Draw)
    }
}

@ExperimentalAnimationApi
private fun NavGraphBuilder.addDraw(
    navController: NavController,
    root: Screen
) {
    composable(LeafScreen.Draw.createRoute(root)) {
        Draw(
            navigateUp = { navController.navigateUp() }
        )
    }
}
