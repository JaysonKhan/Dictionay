package uz.gita.HK_dictionary

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import uz.gita.HK_dictionary.data.source.local.AppDatabase

class App: Application() {
    companion object{
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        AppDatabase.init(this)
    }
}