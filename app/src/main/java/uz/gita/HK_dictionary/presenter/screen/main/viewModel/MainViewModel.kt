package uz.gita.HK_dictionary.presenter.screen.main.viewModel

import androidx.lifecycle.LiveData

interface MainViewModel {
    val openFavScreen: LiveData<Unit>

    fun openFavouriteScreen()

    fun saveCurrentPos(pos: Int)

}