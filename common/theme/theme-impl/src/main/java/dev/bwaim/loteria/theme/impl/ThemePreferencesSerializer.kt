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
            ThemePreferences.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }

    @Suppress("BlockingMethodInNonBlockingContext")
    override suspend fun writeTo(t: ThemePreferences, output: OutputStream) {
        t.writeTo(output)
    }
}
