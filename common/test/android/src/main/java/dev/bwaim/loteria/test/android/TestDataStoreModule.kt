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

package dev.bwaim.loteria.test.android

import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import dev.bwaim.loteria.theme.impl.DataStoreModule
import dev.bwaim.loteria.theme.impl.ThemePreferences
import dev.bwaim.loteria.theme.impl.ThemePreferencesSerializer
import org.junit.rules.TemporaryFolder
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DataStoreModule::class]
)
object TestDataStoreModule {
    @Provides
    @Singleton
    fun providesThemePreferencesDataStore(
        themePreferencesSerializer: ThemePreferencesSerializer,
        tmpFolder: TemporaryFolder
    ): DataStore<ThemePreferences> =
        tmpFolder.testThemePreferencesDataStore(themePreferencesSerializer)
}

fun TemporaryFolder.testThemePreferencesDataStore(
    themePreferencesSerializer: ThemePreferencesSerializer = ThemePreferencesSerializer()
) = DataStoreFactory.create(
    serializer = themePreferencesSerializer,
) {
    newFile("theme_preferences_test.pb")
}
