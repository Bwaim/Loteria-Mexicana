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

import android.content.Context
import androidx.startup.Initializer
import timber.log.Timber
import java.util.regex.Pattern

internal class TimberInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        val tree = if (BuildConfig.DEBUG) {
            LoteriaDebugTree()
        } else {
            ReleaseTree()
        }
        Timber.plant(tree)
    }

    override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()

    /**
     * Special version of [Timber.DebugTree] which is tailored for Timber being wrapped
     * within another class.
     */
    private class LoteriaDebugTree : Timber.DebugTree() {
        override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
            super.log(priority, createClassTag(), message, t)
        }

        private fun createClassTag(): String {
            val stackTrace = Throwable().stackTrace
            check(stackTrace.size > CALL_STACK_INDEX) {
                "Synthetic stacktrace didn't have enough elements: are you using proguard?"
            }
            var tag = stackTrace[CALL_STACK_INDEX].className
            val m = ANONYMOUS_CLASS.matcher(tag)
            if (m.find()) {
                tag = m.replaceAll("")
            }
            tag = tag.substring(tag.lastIndexOf('.') + 1)
            // Tag length limit was removed in API 24.
            return tag
        }

        private companion object {
            const val CALL_STACK_INDEX = 7
            val ANONYMOUS_CLASS: Pattern = Pattern.compile("(\\$\\d+)+$")
        }
    }
}
