package dev.bwaim.loteria.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import java.security.AccessControlContext

@Database(entities = [Settings::class], version = 1, exportSchema = false)
public abstract class AppDatabase: RoomDatabase() {

    public abstract fun settingsDao(): SettingsDao

    public companion object{
        private var INSTANCE: AppDatabase? = null

        public fun getDatabase(context: Context): AppDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null) {
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_databse"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}