package uz.gita.HK_dictionary.presenter.page.en_uz.viewmodel

import android.database.Cursor
import androidx.lifecycle.LiveData
import uz.gita.HK_dictionary.data.source.local.entity.DictionaryEntity

interface EnUzRepository {
    val getAllEnglishWord: LiveData<Cursor>
    val getEnglishWord: LiveData<Cursor>
    val updateCursorLiveData: LiveData<Unit>

    fun searchWord(searchWord: String)
    fun updateDictionary(dictionary: DictionaryEntity)
    fun getAllEnglishWord()
}