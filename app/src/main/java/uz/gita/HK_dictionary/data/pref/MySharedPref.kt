package uz.gita.dictionarynewhk.data.local.pref

import android.content.Context
import uz.gita.HK_dictionary.App

class MySharedPref {
    companion object{
        private val instance = MySharedPref()
        private val pref = App.instance.getSharedPreferences("Settings", Context.MODE_PRIVATE)

        fun getInstance() = instance
    }
    var first: Boolean
        set(bool) = pref.edit().putBoolean("ISFIRST", bool).apply()
        get() = pref.getBoolean("ISFIRST", false)
}