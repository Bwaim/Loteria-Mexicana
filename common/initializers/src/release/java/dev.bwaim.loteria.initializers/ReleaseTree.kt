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

import android.util.Log
import com.google.firebase.crashlytics.FirebaseCrashlytics
import dev.bwaim.loteria.initializers.RemoteCrashKeys.KEY_MESSAGE
import dev.bwaim.loteria.initializers.RemoteCrashKeys.KEY_PRIORITY
import dev.bwaim.loteria.initializers.RemoteCrashKeys.KEY_TAG
import timber.log.Timber

internal class ReleaseTree : Timber.Tree() {
    private val crashlytics = FirebaseCrashlytics.getInstance()

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        if (priority == Log.VERBOSE || priority == Log.DEBUG || priority == Log.INFO) {
            return
        }

        crashlytics.run {
            setCustomKey(KEY_PRIORITY, priority)
            setCustomKey(KEY_TAG, tag ?: "")
            setCustomKey(KEY_MESSAGE, message)
        }

        val exception = t ?: Exception(message)
        crashlytics.recordException(exception)
    }
}
