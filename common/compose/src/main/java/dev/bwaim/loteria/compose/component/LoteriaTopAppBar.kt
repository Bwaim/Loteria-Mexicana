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

package dev.bwaim.loteria.compose.component

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import dev.bwaim.loteria.compose.LoteriaPreviews
import dev.bwaim.loteria.compose.R.string

@Composable
public fun LoteriaTopAppBar(
    title: String?,
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
) {
    TopAppBar(
        title = { title?.let { TopAppBarTitle(text = it) } },
        navigationIcon = navigationIcon,
        actions = actions,
    )
}

@Composable
public fun LoteriaTopAppBarWithBackButton(
    title: String?,
    backAction: () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
) {
    LoteriaTopAppBar(
        title = title,
        navigationIcon = { BackButton { backAction() } },
        actions = actions,
    )
}

@Composable
public fun BackButton(onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = stringResource(string.toolbar_up_description),
        )
    }
}

@Composable
public fun TopAppBarTitle(text: String) {
    Text(
        text = text,
        overflow = TextOverflow.Ellipsis,
        maxLines = 1,
    )
}

@Composable
public fun ActionButton(
    imageVector: ImageVector,
    contentDescription: String?,
    onClick: () -> Unit = {},
) {
    IconButton(
        onClick = onClick,
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = contentDescription,
        )
    }
}

@LoteriaPreviews
@Composable
private fun LoteriaTopAppBarWithoutActionsPreview() {
    LoteriaTopAppBar(title = "Loteria TopAppBar")
}

@LoteriaPreviews
@Composable
private fun LoteriaTopAppBarWithActionsPreview() {
    LoteriaTopAppBar(
        title = "Loteria TopAppBar",
        actions = {
            ActionButton(
                imageVector = Filled.Settings,
                contentDescription = null,
            )
        },
    )
}

@LoteriaPreviews
@Composable
private fun LoteriaTopAppBarWithBackButtonPreview() {
    LoteriaTopAppBarWithBackButton(
        title = "Loteria TopAppBar",
        actions = {
            ActionButton(
                imageVector = Filled.Settings,
                contentDescription = null,
            )
        },
    )
}
