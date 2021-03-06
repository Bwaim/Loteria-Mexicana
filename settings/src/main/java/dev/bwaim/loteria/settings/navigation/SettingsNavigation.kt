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

package dev.bwaim.loteria.settings.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import dev.bwaim.loteria.navigation.LoteriaNavigationDestination
import dev.bwaim.loteria.settings.SettingsRoute

object SettingsDestination : LoteriaNavigationDestination {
    override val route = "settings_route"
    override val destination = "settings_destination"
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.settingsGraph(
    onBackClick: () -> Unit
) {
    composable(route = SettingsDestination.route) {
        SettingsRoute(
            onBackClick = onBackClick
        )
    }
}
