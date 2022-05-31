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

package dev.bwaim.loteria.compose

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import dev.bwaim.loteria.compose.theme.LoteriaTheme

@Composable
public fun BackButton(onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = stringResource(R.string.toolbar_up_description),
        )
    }
}

@Composable
public fun TopAppBarTitle(text: String) {
    Text(
        text = text,
        overflow = TextOverflow.Ellipsis,
        maxLines = 1,
        style = LoteriaTheme.typography.title2
    )
}

@Composable
public fun animatedAppBarElevation(
    lazyListState: LazyListState,
    startValue: Dp = 0.dp,
    endValue: Dp = AppBarDefaults.TopAppBarElevation
): State<Dp> {
    return animateDpAsState(
        if (lazyListState.isScrolled()) {
            endValue
        } else {
            startValue
        }
    )
}
