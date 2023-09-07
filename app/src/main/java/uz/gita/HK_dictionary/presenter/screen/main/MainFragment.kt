package uz.gita.HK_dictionary.presenter.screen.main

import android.os.Bundle
import android.view.View
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.tabs.TabLayoutMediator
import uz.gita.HK_dictionary.R
import uz.gita.HK_dictionary.databinding.FragmentMain2Binding
import uz.gita.HK_dictionary.presenter.adapter.MyAdapter
import uz.gita.HK_dictionary.presenter.page.en_uz.EnUzFragment
import uz.gita.HK_dictionary.presenter.page.uz_en.UzEnFragment
import uz.gita.HK_dictionary.presenter.screen.main.viewModel.impl.MainViewModelImpl
import uz.gita.HK_dictionary.util.myApply

class MainFragment : Fragment(R.layout.fragment_main2) {
    private val binding by viewBinding(FragmentMain2Binding::bind)
    private val viewModel by viewModels<MainViewModelImpl>()

    private var tabTitle = arrayOf("Eng-Uzb", "Uzb-Eng")

    private lateinit var enUzPage: EnUzFragment
    private lateinit var uzEnPage: UzEnFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enUzPage = EnUzFragment()
        uzEnPage = UzEnFragment()

        viewModel.openFavScreen.observe(this, openFavScreenObserver)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.myApply {
        outlinedTextField.clearFocus()

        val myAdapter = MyAdapter(childFragmentManager, viewLifecycleOwner.lifecycle, enUzPage, uzEnPage)

        includedLayout.viewPager2.adapter = myAdapter
        TabLayoutMediator(tabLayout, includedLayout.viewPager2) { tab, position -> tab.text = tabTitle[position] }.attach()

        floatingBtn.setOnClickListener {
            viewModel.openFavouriteScreen()
        }

        outlinedTextField.editText?.doOnTextChanged { text, start, before, count ->

            if(viewModel.pagePosition == 0) {
                enUzPage.searchQuery(text.toString())
            } else {
                uzEnPage.searchQuery(text.toString())
            }
        }
        
        outlinedTextField.setEndIconOnClickListener {
            if(viewModel.pagePosition == 0) {
                enUzPage.searchQuery("")
            } else {
                uzEnPage.searchQuery("")
            }
            outlinedTextField.editText?.setText("")
            outlinedTextField.clearFocus()
        }

        includedLayout.viewPager2.registerOnPageChangeCallback(myChangeCallBack)
    }

    private val myChangeCallBack = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            viewModel.saveCurrentPos(position)
        }
    }

    private val openFavScreenObserver = Observer<Unit> {
        findNavController().navigate(R.id.action_mainFragment_to_favouriteFragment)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.includedLayout.viewPager2.unregisterOnPageChangeCallback(myChangeCallBack)
    }

}