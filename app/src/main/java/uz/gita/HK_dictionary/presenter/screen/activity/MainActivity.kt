package uz.gita.HK_dictionary.presenter.screen.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import uz.gita.HK_dictionary.R


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun getCurrentFragment(): Fragment {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_graph) as NavHostFragment
        val currentFragment = navHostFragment.childFragmentManager.findFragmentById(navHostFragment.navController.currentDestination!!.id)
        return currentFragment!!
    }
}