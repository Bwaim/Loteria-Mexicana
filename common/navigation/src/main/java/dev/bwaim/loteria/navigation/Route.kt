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

@file:OptIn(ExperimentalAnimationApi::class, ExperimentalMaterialNavigationApi::class)

package dev.bwaim.loteria.navigation

import android.net.Uri
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.bottomSheet

public interface Route {
    public val baseRoutePattern: String
    public val mandatoryArguments: List<NamedNavArgument>
    public val optionalArguments: List<NamedNavArgument>

    public val route: String
        get() = "$baseRoutePattern${addOptionalParameters()}"

    public fun buildRoute(
        params: List<Pair<String, Any>> = emptyList(),
        optionalParams: List<Pair<String, Any>> = emptyList(),
    ): String {
        var finalRoute = baseRoutePattern
        params.forEach { (argName, value) ->
            finalRoute = finalRoute.replace("{$argName}", value.encodedValue())
        }

        finalRoute += optionalParams.joinToString(prefix = "?", separator = "&") {
            "${it.first}=${it.second.encodedValue()}"
        }

        return finalRoute
    }

    context(NavGraphBuilder)
    public fun composable(
        deepLinks: List<NavDeepLink> = emptyList(),
        enterTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?)? = null,
        exitTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?)? = null,
        popEnterTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?)? =
            enterTransition,
        popExitTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?)? =
            exitTransition,
        content: @Composable AnimatedVisibilityScope.(NavBackStackEntry) -> Unit,
    ) {
        composable(
            route = this@Route.route,
            arguments = mandatoryArguments + optionalArguments,
            deepLinks = deepLinks,
            enterTransition = enterTransition,
            exitTransition = exitTransition,
            popEnterTransition = popEnterTransition,
            popExitTransition = popExitTransition,
            content = content,
        )
    }

    context(NavGraphBuilder)
    public fun bottomSheet(
        isFullScreen: Boolean = false,
        deepLinks: List<NavDeepLink> = emptyList(),
        content: @Composable ColumnScope.(backstackEntry: NavBackStackEntry) -> Unit,
    ) {
        bottomSheet(
            route = this@Route.route,
            arguments = mandatoryArguments + optionalArguments,
            deepLinks = deepLinks,
            content = { backstackEntry ->
                val heightModifier = if (isFullScreen) {
                    val height = (LocalConfiguration.current.screenHeightDp - 54).dp
                    Modifier.height(height)
                } else {
                    Modifier
                }
                Box(
                    modifier = heightModifier,
                ) {
                    Column {
                        content(backstackEntry)
                    }
                }
            },
        )
    }

    private fun addOptionalParameters(): String =
        optionalArguments.joinToString(separator = "&", prefix = "?") {
            "${it.name}={${it.name}}"
        }

    private fun Any.encodedValue(): String = when (this) {
        is String -> Uri.encode(this)
        else -> this.toString()
    }
}
