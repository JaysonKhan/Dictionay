package uz.gita.HK_dictionary.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uz.gita.HK_dictionary.data.source.local.dao.DictionaryDao
import uz.gita.HK_dictionary.data.source.local.entity.DictionaryEntity

@Database(entities = [DictionaryEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getDao(): DictionaryDao

    companion object {
        private lateinit var instance: AppDatabase

        fun init(context: Context) {
            if (!(::instance.isInitialized)) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "Dictionary.db"
                )
                    .createFromAsset("Dictionary.db")
                    .allowMainThreadQueries()
                    .build()
            }
        }

        fun getInstance() = instance
    }
}