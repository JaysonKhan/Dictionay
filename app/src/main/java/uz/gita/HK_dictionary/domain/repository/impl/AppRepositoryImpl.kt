package uz.gita.HK_dictionary.domain.repository.impl

import android.database.Cursor
import uz.gita.HK_dictionary.data.source.local.AppDatabase
import uz.gita.HK_dictionary.data.source.local.entity.DictionaryEntity
import uz.gita.HK_dictionary.domain.repository.AppRepository

class AppRepositoryImpl: AppRepository {
    private val database = AppDatabase.getInstance()

    companion object {
        private lateinit var instance: AppRepository
        fun getInstance(): AppRepository {
            if(!(::instance.isInitialized)){
                instance = AppRepositoryImpl()
            }

            return instance
        }
    }

    override fun getAllEnglishWords(): Cursor {
        return database.getDao().getAllEnglishWords()
    }

    override fun getAllUzbekWords(): Cursor {
        return database.getDao().getAllUzbekWords()
    }

    override fun updateDictionary(dictionary: DictionaryEntity) {
        database.getDao().updateDictionary(dictionary)
    }

    override fun searchEnglishWord(searchEnglishWord: String): Cursor {
        return database.getDao().searchEnglishWord(searchEnglishWord)
    }

    override fun searchUzbekWord(searchEnglishWord: String): Cursor {
        return database.getDao().searchUzbekWord(searchEnglishWord)
    }

    override fun getAllFavourites(): Cursor {
        return database.getDao().getAllFavourites()
    }
}