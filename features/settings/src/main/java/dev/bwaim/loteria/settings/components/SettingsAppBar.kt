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

package dev.bwaim.loteria.settings.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import dev.bwaim.loteria.compose.component.LoteriaTopAppBarWithBackButton
import dev.bwaim.loteria.compose.extensions.testTag
import dev.bwaim.loteria.settings.R.string

public const val SETTINGS_TOP_APP_BAR: String = "SettingsTopAppBar"

@Composable
internal fun SettingsAppBar(
    onBackClick: () -> Unit,
) {
    LoteriaTopAppBarWithBackButton(
        title = stringResource(id = string.settings_title),
        modifier = Modifier.testTag(SETTINGS_TOP_APP_BAR),
        backAction = onBackClick,
    )
}
