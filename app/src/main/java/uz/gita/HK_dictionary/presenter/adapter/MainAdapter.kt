package uz.gita.HK_dictionary.presenter.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class MyAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
    private val enUzPage: uz.gita.HK_dictionary.presenter.page.en_uz.EnUzFragment,
    private val uzEnPage: uz.gita.HK_dictionary.presenter.page.uz_en.UzEnFragment
) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> {
                enUzPage
            }
            else -> {
                uzEnPage
            }
        }
    }
}