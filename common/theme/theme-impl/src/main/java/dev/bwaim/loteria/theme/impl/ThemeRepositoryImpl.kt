package dev.bwaim.loteria.theme.impl

import android.content.Context
import androidx.annotation.VisibleForTesting
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import dev.bwaim.loteria.theme.Theme
import dev.bwaim.loteria.theme.ThemeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import javax.inject.Inject

//private const val DATA_STORE_FILE_NAME = "theme_prefs.pb"
//
//@get:VisibleForTesting
//public val Context.dataStore: DataStore<ThemePreferences> by dataStore(
//    fileName = DATA_STORE_FILE_NAME,
//    serializer = ThemePreferencesSerializer
//)

@VisibleForTesting
public class ThemeRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val dataStore: DataStore<ThemePreferences>
) : ThemeRepository {

    override fun observeTheme(): Flow<Theme> {
        return dataStore
            .data
            .distinctUntilChanged()
            .map { prefs ->
                ThemeHelper.fromPreferences(prefs.theme)
            }
    }

    override suspend fun setTheme(theme: Theme) {
        dataStore.updateData { prefs ->
            prefs
                .toBuilder()
                .setTheme(theme.value)
                .build()
        }
    }
}
