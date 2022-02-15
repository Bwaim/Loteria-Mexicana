package dev.bwaim.loteria.data.database

import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
public interface SettingsDao {

    @Query("SELECT * FROM settings_table")
    public fun readData(): LiveData<List<Settings>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public suspend fun addSettings(settings: Settings)
}