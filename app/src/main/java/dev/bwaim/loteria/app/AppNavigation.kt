package dev.bwaim.loteria.app

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.navigation
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dev.bwaim.loteria.settings.Settings

internal sealed class Screen(val route: String) {
    object MainMenu : Screen("mainMenu")
    object Settings : Screen("settings")
}

private sealed class LeafScreen(
    private val route: String,
) {
    fun createRoute(root: Screen) = "${root.route}/$route"

    object MainMenu : LeafScreen("mainMenu")
    object Settings : LeafScreen("settings")
}

// TODO: remove the transition or change it when https://github.com/google/accompanist/issues/773
//  is solved
@ExperimentalAnimationApi
@Composable
internal fun AppNavigation() {
    val navController = rememberAnimatedNavController()
    AnimatedNavHost(
        navController,
        startDestination = Screen.MainMenu.route,
        enterTransition = { fadeIn(animationSpec = tween(700)) },
        exitTransition = { fadeOut(animationSpec = tween(700)) }
    ) {
        addMainMenuTopLevel(navController)
        addSettingsTopLevel(navController)
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
            startDraw = {
                /*navController.navigate()*/
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
