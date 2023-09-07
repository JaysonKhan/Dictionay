package uz.gita.HK_dictionary.presenter.page.uz_en.viewmodel

import android.database.Cursor
import androidx.lifecycle.LiveData
import uz.gita.HK_dictionary.data.source.local.entity.DictionaryEntity

interface UzEnRepository {
    val getAllUzbekWord: LiveData<Cursor>
    val getUzbekWord: LiveData<Cursor>
    val updateCursorLiveData: LiveData<Unit>

    fun searchWord(searchWord: String)
    fun updateDictionary(dictionary: DictionaryEntity)
    fun getAllUzbekWord()
}