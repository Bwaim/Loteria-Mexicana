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

import app.cash.turbine.test
import dev.bwaim.loteria.test.android.testLocalePreferencesDataStore
import java.util.Locale
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder

internal class LocaleRepositoryImplTest {
    private lateinit var subject: LocaleRepositoryImpl

    @get:Rule
    val tmpFolder: TemporaryFolder = TemporaryFolder.builder().assureDeletion().build()

    @Before
    fun setUp() {
        subject = LocaleRepositoryImpl(tmpFolder.testLocalePreferencesDataStore())
    }

    @Test
    fun firstLocale_isLocalPreferenceDefaultValue() = runTest {
        Assert.assertEquals(
            Locale(""),
            subject.observeLocale().first()
        )
    }

    // This test fails on Windows : https://github.com/android/nowinandroid/issues/98
    @Test
    fun observeLocale_outputsLocalePreferences() = runTest {
        val localeValue = Locale("fr")

        subject.observeLocale().test {
            Assert.assertEquals(
                Locale(""),
                awaitItem()
            )

            subject.setLocale(localeValue)

            Assert.assertEquals(
                localeValue,
                awaitItem()
            )

            cancel()
        }
    }
}
