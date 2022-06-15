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

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import dev.bwaim.loteria.draw.DrawRoute
import dev.bwaim.loteria.navigation.LoteriaNavigationDestination

object DrawDestination : LoteriaNavigationDestination {
    override val route = "draw_route"
    override val destination = "draw_destination"
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.drawGraph(
    onBackClick: () -> Unit
) {
    composable(route = DrawDestination.route) {
        DrawRoute(
            onBackClick = onBackClick
        )
    }
}
