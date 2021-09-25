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
