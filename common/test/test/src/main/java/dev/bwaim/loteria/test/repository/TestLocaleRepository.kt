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

package dev.bwaim.loteria.test.repository

import dev.bwaim.loteria.locale.LocaleRepository
import java.util.Locale
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

public class TestLocaleRepository : LocaleRepository {

    private var localeStateFlow = MutableStateFlow(Locale("fr"))

    override fun observeLocale(): Flow<Locale> = localeStateFlow

    override suspend fun setLocale(locale: Locale) {
        localeStateFlow.update {
            locale
        }
    }
}
