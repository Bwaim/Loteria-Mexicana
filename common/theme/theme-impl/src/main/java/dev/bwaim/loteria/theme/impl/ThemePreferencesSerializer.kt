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
import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import java.io.InputStream
import java.io.OutputStream
import javax.inject.Inject

@VisibleForTesting
public class ThemePreferencesSerializer @Inject constructor() : Serializer<ThemePreferences> {
    override val defaultValue: ThemePreferences get() = ThemePreferences.getDefaultInstance()

    @Suppress("BlockingMethodInNonBlockingContext")
    override suspend fun readFrom(input: InputStream): ThemePreferences =
        try {
            @Suppress("BlockingMethodInNonBlockingContext")
            ThemePreferences.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }

    @Suppress("BlockingMethodInNonBlockingContext")
    override suspend fun writeTo(t: ThemePreferences, output: OutputStream) {
        @Suppress("BlockingMethodInNonBlockingContext")
        t.writeTo(output)
    }
}
