package dev.bwaim.loteria.data.settings.settings

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [SettingsEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun settingsDao(): SettingsDao
}