package dev.bwaim.loteria.app

import androidx.navigation.NamedNavArgument
import dev.bwaim.loteria.common.navigation.NavigationCommand

public object MainDirections {
    public val Default: NavigationCommand = object : NavigationCommand {
        override val arguments = emptyList<NamedNavArgument>()
        override val destination = ""
    }

    public val root: NavigationCommand = object : NavigationCommand {
        override val arguments = emptyList<NamedNavArgument>()
        override val destination = "main_graph"
    }

    public val mainMenu: NavigationCommand = object : NavigationCommand {
        override val arguments = emptyList<NamedNavArgument>()
        override val destination = "main_menu"
    }
}