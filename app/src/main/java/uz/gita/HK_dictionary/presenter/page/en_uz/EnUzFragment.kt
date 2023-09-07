package uz.gita.HK_dictionary.presenter.page.en_uz

import android.app.Dialog
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import by.kirich1409.viewbindingdelegate.viewBinding
import kotlinx.coroutines.launch
import uz.gita.HK_dictionary.R
import uz.gita.HK_dictionary.databinding.FragmentEnUzBinding
import uz.gita.HK_dictionary.presenter.adapter.MyEnUzAdapter
import uz.gita.HK_dictionary.presenter.page.en_uz.viewmodel.EnUzRepository
import uz.gita.HK_dictionary.presenter.page.en_uz.viewmodel.impl.EnUzRepositoryImpl
import java.util.*

class EnUzFragment : Fragment(R.layout.fragment_en_uz), TextToSpeech.OnInitListener {
    private val binding by viewBinding(FragmentEnUzBinding::bind)
    private val viewModelEnglish: EnUzRepository by viewModels<EnUzRepositoryImpl>()
    private var currentQuery: String = ""
    private val myAdapter: MyEnUzAdapter by lazy { MyEnUzAdapter() }
    private var tts: TextToSpeech? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {

        viewModelEnglish.getEnglishWord.observe(viewLifecycleOwner) {
            myAdapter.submitCursor(it)
        }

        myAdapter.showDialogClickListener { dictionary ->
            val dialog = Dialog(requireContext())
            dialog.setCancelable(true)
            dialog.setContentView(R.layout.dialog_custom_en)
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

            val englishText: AppCompatTextView = dialog.findViewById(R.id.text_english_word_dialog)
            val transcript: AppCompatTextView =
                dialog.findViewById(R.id.text_english_transcript_dialog)
            val uzbek: AppCompatTextView = dialog.findViewById(R.id.text_uzbek_dialog)

            englishText.text = dictionary.english
            transcript.text = dictionary.transcript
            uzbek.text = dictionary.uzbek

            val okBtn: AppCompatTextView = dialog.findViewById(R.id.okay_btn_dialog)
            okBtn.setOnClickListener { dialog.dismiss() }

            val soundBtn: AppCompatImageView = dialog.findViewById(R.id.im_sound_dialog)
            val copyBtn: AppCompatImageView = dialog.findViewById(R.id.im_copy_dialog)
            val favouriteBtn: AppCompatImageView = dialog.findViewById(R.id.im_favorite_dialog)

            if (dictionary.favourite == 0) {
                favouriteBtn.setImageResource(R.drawable.not_favorite)
            } else {
                favouriteBtn.setImageResource(R.drawable.favorite)
            }

            soundBtn.setOnClickListener {
                speakOut(dictionary.english)
            }

            copyBtn.setOnClickListener {
                val clipBoard =
                    requireActivity().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                val clip = ClipData.newPlainText(
                    dictionary.english, "${dictionary.english}\n\n${dictionary.transcript}\n\n" +
                            "${dictionary.uzbek}\n"
                )
                clipBoard.setPrimaryClip(clip)
            }

            favouriteBtn.setOnClickListener {
                if (dictionary.favourite == 0) {
                    dictionary.favourite = 1
                    viewModelEnglish.updateDictionary(dictionary)
                    favouriteBtn.setImageResource(R.drawable.favorite)
                } else {
                    favouriteBtn.setImageResource(R.drawable.not_favorite)
                    dictionary.favourite = 0
                    viewModelEnglish.updateDictionary(dictionary)
                }
            }

            okBtn.setOnClickListener {
                dialog.dismiss()
            }
            dialog.create()
            dialog.show()
        }

        initRecyclerView()

        tts = TextToSpeech(requireContext(), this@EnUzFragment)

        lifecycleScope.launch {
            viewModelEnglish.getAllEnglishWord()
        }

        viewModelEnglish.getAllEnglishWord.observe(viewLifecycleOwner) {
            myAdapter.submitCursor(it)
        }

        viewModelEnglish.updateCursorLiveData.observe(viewLifecycleOwner) {
            searchQuery(currentQuery)
        }
    }

    private fun initRecyclerView() {
        binding.apply {
            recyclerview.adapter = myAdapter
            recyclerview.addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
        }
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = tts!!.setLanguage(Locale.US)

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "The Language not supported!")
            }
        }
    }

    private fun speakOut(englishWord: String) {
        tts!!.speak(englishWord, TextToSpeech.QUEUE_FLUSH, null, "")
    }

    fun searchQuery(query: String) {
        currentQuery = query
        if(query.isEmpty()) {
            viewModelEnglish.getAllEnglishWord()
            binding.textNotFound.visibility = View.GONE
        }
        else viewModelEnglish.searchWord(query)
    }
}