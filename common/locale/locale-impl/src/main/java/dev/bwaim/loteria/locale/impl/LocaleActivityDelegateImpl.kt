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

import dev.bwaim.loteria.coroutines.ApplicationScope
import dev.bwaim.loteria.locale.LocaleService
import java.util.Locale
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.runBlocking

@Singleton
public class LocaleActivityDelegateImpl @Inject constructor(
    @ApplicationScope applicationScope: CoroutineScope,
    localeService: LocaleService
) {
    public val locale: StateFlow<Locale> = localeService
        .observeLocale()
        .stateIn(
            applicationScope,
            SharingStarted.Eagerly,
            runBlocking { localeService.observeLocale().first() }
        )
}
