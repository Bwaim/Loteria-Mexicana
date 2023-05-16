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

package dev.bwaim.loteria.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import dev.bwaim.loteria.compose.extensions.testTag
import dev.bwaim.loteria.home.components.HomeAppBar
import dev.bwaim.loteria.localization.R.string

public const val START_BUTTON: String = "StartButton"

@Composable
public fun HomeRoute(
    modifier: Modifier = Modifier,
    navigateToSettings: () -> Unit,
    navigateToDraw: () -> Unit,
) {
    HomeScreen(
        navigateToSettings = navigateToSettings,
        navigateToDraw = navigateToDraw,
    )
}

@Composable
private fun HomeScreen(
    navigateToSettings: () -> Unit,
    navigateToDraw: () -> Unit,
) {
    Scaffold(
        topBar = {
            HomeAppBar(
                navigateToSettings = navigateToSettings,
            )
        },
    ) { contentPadding ->
        Column(
            Modifier
                .padding(contentPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Button(
                onClick = { navigateToDraw() },
                modifier = Modifier.testTag(START_BUTTON),
            ) {
                Text(text = stringResource(id = string.start_menu))
            }
        }
    }
}
