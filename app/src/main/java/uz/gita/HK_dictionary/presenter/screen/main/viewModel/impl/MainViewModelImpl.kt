package uz.gita.HK_dictionary.presenter.screen.main.viewModel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.gita.HK_dictionary.domain.repository.AppRepository
import uz.gita.HK_dictionary.domain.repository.impl.AppRepositoryImpl
import uz.gita.HK_dictionary.presenter.screen.main.viewModel.MainViewModel

class MainViewModelImpl : ViewModel(), MainViewModel {
    var pagePosition = 0
        private set

    private val repository: AppRepository = AppRepositoryImpl.getInstance()

    override val openFavScreen = MutableLiveData<Unit>()


    override fun openFavouriteScreen() {
        openFavScreen.value = Unit
    }

    override fun saveCurrentPos(pos: Int) {
        pagePosition = pos
    }
}