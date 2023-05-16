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

package dev.bwaim.loteria.initializers

import android.content.Context
import androidx.startup.Initializer
import timber.log.Timber

internal class TimberInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        val tree = getTimberTree()
        Timber.plant(tree)
    }

    override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()
}
