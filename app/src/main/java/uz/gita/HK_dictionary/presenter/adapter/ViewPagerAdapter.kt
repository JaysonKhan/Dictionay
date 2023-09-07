package uz.gita.HK_dictionary.presenter.adapter

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import uz.gita.HK_dictionary.presenter.page.PageVp

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle):FragmentStateAdapter(fragmentManager, lifecycle) {
    private lateinit var listener: (() -> Unit)
    override fun getItemCount(): Int = 6

    override fun createFragment(position: Int): Fragment  = PageVp().apply {
        arguments = bundleOf(Pair("POS", position))
        letsListener = listener
    }
    fun setListener(block: ()->Unit){
        listener = block
    }
}