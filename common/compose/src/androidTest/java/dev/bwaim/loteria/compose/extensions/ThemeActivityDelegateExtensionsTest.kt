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

package dev.bwaim.loteria.compose.extensions

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.test.junit4.createComposeRule
import com.google.common.truth.Truth
import dev.bwaim.loteria.compose.theme.LoteriaTheme
import dev.bwaim.loteria.theme.Theme
import dev.bwaim.loteria.theme.ThemeService
import dev.bwaim.loteria.theme.impl.ThemeActivityDelegateImpl
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import junitparams.JUnitParamsRunner
import junitparams.Parameters
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestScope
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(JUnitParamsRunner::class)
internal class ThemeActivityDelegateExtensionsTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var themeActivityDelegate: ThemeActivityDelegateImpl

    @MockK
    private lateinit var mockThemeService: ThemeService

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    private fun data() = arrayOf(
        arrayOf(Theme.LIGHT, false),
        arrayOf(Theme.DARK, true),
        arrayOf(Theme.SYSTEM, null),
        arrayOf(Theme.BATTERY_SAVER, null),
    )

    @Test
    @Parameters(method = "data")
    fun shouldReturnSystemValue_whenThemeIsBatterySaver(theme: Theme, expected: Boolean?) {
        var result: Boolean? = null
        var expectedResult: Boolean? = null

        initDelegate {
            every { mockThemeService.observeTheme() } returns flowOf(theme)
        }

        testContent {
            expectedResult = expected ?: isSystemInDarkTheme()
            result = themeActivityDelegate.shouldUseDarkColors()
        }

        Truth.assertThat(result).isEqualTo(expectedResult)
    }

    private fun initDelegate(mockConfig: () -> Unit) {
        mockConfig()
        themeActivityDelegate = ThemeActivityDelegateImpl(TestScope(), mockThemeService)
    }

    private fun <T> testContent(call: @Composable () -> T) {
        composeTestRule.setContent {
            LoteriaTheme {
                call()
            }
        }
    }
}
