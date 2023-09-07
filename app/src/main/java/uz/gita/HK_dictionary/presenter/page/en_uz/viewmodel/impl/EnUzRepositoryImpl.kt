package uz.gita.HK_dictionary.presenter.page.en_uz.viewmodel.impl

import android.database.Cursor
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.gita.HK_dictionary.data.source.local.entity.DictionaryEntity
import uz.gita.HK_dictionary.domain.repository.AppRepository
import uz.gita.HK_dictionary.domain.repository.impl.AppRepositoryImpl
import uz.gita.HK_dictionary.presenter.page.en_uz.viewmodel.EnUzRepository

class EnUzRepositoryImpl: ViewModel(), EnUzRepository {

    private val repository: AppRepository = AppRepositoryImpl.getInstance()

    override val getAllEnglishWord = MutableLiveData<Cursor>()
    override val getEnglishWord = MutableLiveData<Cursor>()
    override val updateCursorLiveData = MutableLiveData<Unit>()


    override fun searchWord(searchWord: String) {
        getEnglishWord.value = repository.searchEnglishWord(searchWord)
    }

    override fun updateDictionary(dictionary: DictionaryEntity) {
        repository.updateDictionary(dictionary)
        updateCursorLiveData.value = Unit
//        getAllEnglishWord()
    }

    override fun getAllEnglishWord() {
        getAllEnglishWord.value = repository.getAllEnglishWords()
    }
}