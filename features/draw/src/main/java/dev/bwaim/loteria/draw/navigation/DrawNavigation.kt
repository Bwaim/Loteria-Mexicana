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

package dev.bwaim.loteria.draw.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavGraphBuilder
import dev.bwaim.loteria.draw.DrawRoute
import dev.bwaim.loteria.navigation.Route

private const val drawNavigationRoute: String = "draw"

public object DrawRoute : Route {
    override val baseRoutePattern: String = drawNavigationRoute
    override val mandatoryArguments: List<NamedNavArgument> = emptyList()
    override val optionalArguments: List<NamedNavArgument> = emptyList()
}

public fun NavGraphBuilder.drawScreen(
    onBackClick: () -> Unit,
) {
    DrawRoute.composable {
        DrawRoute(
            onBackClick = onBackClick,
        )
    }
}
