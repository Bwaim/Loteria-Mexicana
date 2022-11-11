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

package dev.bwaim.loteria.locale

import dev.bwaim.loteria.coroutines.IODispatcher
import java.util.Locale
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

public class LocaleService @Inject constructor(
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    private val localeRepository: LocaleRepository
) {

    public suspend fun getLocale(): Locale = withContext(ioDispatcher) {
        observeLocale().first()
    }

    public fun observeLocale(): Flow<Locale> {
        return localeRepository
            .observeLocale()
            .flowOn(ioDispatcher)
    }

    public suspend fun setLocale(locale: Locale) {
        withContext(ioDispatcher) {
            localeRepository.setLocale(locale)
        }
    }
}
