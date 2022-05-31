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

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.AppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
public fun LazyListState.elevation(): Dp {
    return remember(this) {
        derivedStateOf {
            if (firstVisibleItemIndex == 0) {
                minOf(firstVisibleItemScrollOffset.toFloat().dp, AppBarDefaults.TopAppBarElevation)
            } else {
                AppBarDefaults.TopAppBarElevation
            }
        }
    }.value
}

@Composable
public fun LazyListState.isScrolled(): Boolean {
    return remember(this) {
        derivedStateOf { firstVisibleItemIndex > 0 || firstVisibleItemScrollOffset > 0 }
    }.value
}

public object ListDefaults {
    public val VerticalPadding: Dp = 8.dp
}
