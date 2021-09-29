package dev.bwaim.loteria.navigation

import dev.bwaim.loteria.common.navigation.NavigationCommand
import dev.bwaim.loteria.navigation.ExampleDirections.Default
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
public class NavigationManager @Inject constructor() {
    public var commands: MutableStateFlow<NavigationCommand> = MutableStateFlow(Default)

    public fun navigate(
        directions: NavigationCommand
    ) {
        commands.value = directions
    }
}
