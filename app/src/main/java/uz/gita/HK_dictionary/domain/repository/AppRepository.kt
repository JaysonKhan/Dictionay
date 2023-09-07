package uz.gita.HK_dictionary.domain.repository

import android.database.Cursor
import uz.gita.HK_dictionary.data.source.local.entity.DictionaryEntity

interface AppRepository {
    fun getAllEnglishWords(): Cursor
    fun getAllUzbekWords(): Cursor
    fun updateDictionary(dictionary: DictionaryEntity)
    fun searchEnglishWord(searchEnglishWord: String): Cursor
    fun searchUzbekWord(searchEnglishWord: String): Cursor

    fun getAllFavourites(): Cursor
}