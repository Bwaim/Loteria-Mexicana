package dev.bwaim.liberia.settings

import androidx.navigation.NamedNavArgument
import dev.bwaim.loteria.common.navigation.NavigationCommand

public object SettingsDirections {
    public val root: NavigationCommand = object : NavigationCommand {
        override val arguments = emptyList<NamedNavArgument>()
        override val destination = "settings_graph"
    }

    public val settings: NavigationCommand = object : NavigationCommand {
        override val arguments = emptyList<NamedNavArgument>()
        override val destination = "settings"
    }
}
