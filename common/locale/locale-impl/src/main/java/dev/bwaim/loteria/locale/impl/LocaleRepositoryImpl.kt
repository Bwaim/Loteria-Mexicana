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

package dev.bwaim.loteria.locale.impl

import androidx.datastore.core.DataStore
import dev.bwaim.loteria.locale.LocaleRepository
import java.util.Locale
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

internal class LocaleRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<LocalePreferences>
) : LocaleRepository {

    override fun observeLocale(): Flow<Locale> {
        return dataStore
            .data
            .distinctUntilChanged()
            .map { pref ->
                Locale(pref.locale)
            }
    }

    override suspend fun setLocale(locale: Locale) {
        dataStore.updateData {
            it.copy {
                this@copy.locale = locale.language
            }
        }
    }
}
