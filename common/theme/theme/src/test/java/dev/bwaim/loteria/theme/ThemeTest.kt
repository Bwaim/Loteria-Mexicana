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

package dev.bwaim.loteria.theme

import junitparams.JUnitParamsRunner
import junitparams.Parameters
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(JUnitParamsRunner::class)
internal class ThemeTest {

    private fun themeData() = arrayOf(
        arrayOf(Theme.LIGHT.value, Theme.LIGHT),
        arrayOf(Theme.DARK.value, Theme.DARK),
        arrayOf(Theme.SYSTEM.value, Theme.SYSTEM),
        arrayOf(Theme.BATTERY_SAVER.value, Theme.BATTERY_SAVER),
    )

    @Test
    @Parameters(method = "themeData")
    fun givenValue_returnRightTheme(input: String, expected: Theme) {
        val result = Theme.from(input)

        Assert.assertEquals(expected, result)
    }
}
