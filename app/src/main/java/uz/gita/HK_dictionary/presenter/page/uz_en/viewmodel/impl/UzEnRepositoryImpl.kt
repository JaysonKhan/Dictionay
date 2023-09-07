package uz.gita.HK_dictionary.presenter.page.uz_en.viewmodel.impl

import android.database.Cursor
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.gita.HK_dictionary.data.source.local.entity.DictionaryEntity
import uz.gita.HK_dictionary.domain.repository.impl.AppRepositoryImpl
import uz.gita.HK_dictionary.presenter.page.uz_en.viewmodel.UzEnRepository

class UzEnRepositoryImpl: ViewModel(), UzEnRepository {
    private val repository = AppRepositoryImpl.getInstance()

    override val getAllUzbekWord = MutableLiveData<Cursor>()
    override val getUzbekWord = MutableLiveData<Cursor>()
    override val updateCursorLiveData = MutableLiveData<Unit>()

    override fun searchWord(searchWord: String) {
        getUzbekWord.value = repository.searchUzbekWord(searchWord)
    }

    override fun updateDictionary(dictionary: DictionaryEntity) {
        repository.updateDictionary(dictionary)
        updateCursorLiveData.value = Unit
    }

    override fun getAllUzbekWord() {
        getAllUzbekWord.value = repository.getAllUzbekWords()
    }
}