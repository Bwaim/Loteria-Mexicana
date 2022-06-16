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

package dev.bwaim.loteria.app.mainmenu

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.google.accompanist.insets.ui.TopAppBar
import dev.bwaim.loteria.app.R
import dev.bwaim.loteria.compose.TopAppBarTitle

@Composable
public fun MainMenuRoute(
    modifier: Modifier = Modifier,
    navigateToSettings: () -> Unit,
    navigateToDraw: () -> Unit
) {
    MainMenu(
        navigateToSettings = navigateToSettings,
        navigateToDraw = navigateToDraw

    )
}

@Composable
private fun MainMenu(
    navigateToSettings: () -> Unit,
    navigateToDraw: () -> Unit
) {
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            MainMenuAppBar(
                navigateToSettings = navigateToSettings
            )
        }
    ) { contentPadding ->
        Column(
            Modifier
                .padding(contentPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Button(onClick = { navigateToDraw() }) {
                Text(text = stringResource(id = R.string.start_menu))
            }
        }
    }
}

@Composable
private fun MainMenuAppBar(
    navigateToSettings: () -> Unit,
) {
    TopAppBar(
        title = { TopAppBarTitle(text = stringResource(id = R.string.app_name)) },
        modifier = Modifier.windowInsetsPadding(
            WindowInsets
                .safeDrawing.only(WindowInsetsSides.Horizontal + WindowInsetsSides.Top)
        ),
        actions = {
            IconButton(
                onClick = { navigateToSettings() }
            ) {
                Icon(
                    Icons.Filled.Settings,
                    contentDescription = stringResource(id = R.string.settings)
                )
            }
        }
    )
}
