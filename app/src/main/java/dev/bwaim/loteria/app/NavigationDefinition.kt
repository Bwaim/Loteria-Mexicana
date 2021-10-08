package dev.bwaim.loteria.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import dev.bwaim.liberia.settings.Settings
import dev.bwaim.liberia.settings.SettingsDirections
import dev.bwaim.loteria.navigation.NavigationManager

@Composable
public fun NavigationGraph(navigationManager: NavigationManager) {
    val navController = rememberNavController()
    navigationManager.commands.collectAsState().value.also { command ->
        if (command.destination.isNotEmpty()) {
            navController.navigate(command.destination)
        }
    }
    NavHost(
        navController,
        startDestination = MainDirections.root.destination
    ) {
        navigation(
            startDestination = MainDirections.mainMenu.destination,
            route = MainDirections.root.destination
        ) {
            composable(MainDirections.mainMenu.destination) {
                MainMenu(
                    openSettings = { navigationManager.navigate(SettingsDirections.root) },
                    startDraw = { /*navigationManager.navigate()*/ }
                )
            }
        }
        navigation(
            startDestination = SettingsDirections.settings.destination,
            route = SettingsDirections.root.destination
        ) {
            composable(SettingsDirections.settings.destination) {
                Settings()
            }
        }
    }
}
