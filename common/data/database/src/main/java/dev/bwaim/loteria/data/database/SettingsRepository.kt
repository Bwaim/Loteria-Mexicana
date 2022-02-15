package dev.bwaim.loteria.data.database

import androidx.lifecycle.LiveData

public class SettingsRepository(private val settingsDao: SettingsDao) {
    public val readData: LiveData<List<Settings>> = settingsDao.readData()

    public suspend fun addSettings(settings: Settings) {
        settingsDao.addSettings(settings)
    }
}