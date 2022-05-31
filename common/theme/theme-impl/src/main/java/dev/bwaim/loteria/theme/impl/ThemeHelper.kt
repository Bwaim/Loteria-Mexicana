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

package dev.bwaim.loteria.theme.impl

import androidx.annotation.VisibleForTesting
import dev.bwaim.loteria.core.utils.BuildWrapper
import dev.bwaim.loteria.theme.Theme

@VisibleForTesting
public object ThemeHelper {
    @get:VisibleForTesting
    public val defaultTheme: Theme
        get() = if (BuildWrapper.isAtLeastQ) {
            Theme.SYSTEM
        } else {
            Theme.BATTERY_SAVER
        }

    @VisibleForTesting
    public fun fromPreferences(value: String): Theme {
        return when {
            value.isEmpty() -> defaultTheme
            value == Theme.SYSTEM.value && !BuildWrapper.isAtLeastQ -> Theme.BATTERY_SAVER
            value == Theme.BATTERY_SAVER.value && BuildWrapper.isAtLeastQ -> Theme.SYSTEM
            else -> Theme.from(value)
        }
    }
}
