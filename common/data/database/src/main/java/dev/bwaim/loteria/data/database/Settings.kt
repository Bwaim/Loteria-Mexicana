package dev.bwaim.loteria.data.database

import android.content.res.Resources
import androidx.room.Entity

@Entity(tableName = "settings_table")
public data class Settings (
        val theme: Resources.Theme,
        val time: Int,
        )