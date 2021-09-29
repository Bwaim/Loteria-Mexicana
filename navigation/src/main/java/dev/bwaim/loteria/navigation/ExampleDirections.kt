package dev.bwaim.loteria.navigation

import androidx.navigation.NamedNavArgument
import dev.bwaim.loteria.common.navigation.NavigationCommand

public object ExampleDirections {
    public val Default: NavigationCommand = object : NavigationCommand {
        override val arguments = emptyList<NamedNavArgument>()
        override val destination = ""
    }

    public val root: NavigationCommand = object : NavigationCommand {
        override val arguments = emptyList<NamedNavArgument>()
        override val destination = "example_graph"
    }

    public val example: NavigationCommand = object : NavigationCommand {
        override val arguments = emptyList<NamedNavArgument>()
        override val destination = "example"
    }
}
