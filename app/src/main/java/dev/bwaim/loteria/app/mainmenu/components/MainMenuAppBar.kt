/*
 * Copyright 2023 Dev Bwaim team
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

package dev.bwaim.loteria.app.mainmenu.components

import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import dev.bwaim.loteria.app.R.string
import dev.bwaim.loteria.compose.component.ActionButton
import dev.bwaim.loteria.compose.component.LoteriaTopAppBar

@Composable
internal fun MainMenuAppBar(
    navigateToSettings: () -> Unit,
) {
    LoteriaTopAppBar(
        title = stringResource(id = string.app_name),
        actions = {
            ActionButton(
                onClick = { navigateToSettings() },
                imageVector = Filled.Settings,
                contentDescription = stringResource(id = string.settings),
            )
        },
    )
}
