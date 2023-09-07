package uz.gita.HK_dictionary.presenter.page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import uz.gita.HK_dictionary.R

class PageVp : Fragment() {
    lateinit var letsListener: (() -> Unit)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.item_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val imageView: ImageView = view.findViewById(R.id.imageThird)
        val title: TextView =view.findViewById(R.id.textTitleThird)
        val description: TextView = view.findViewById(R.id.textDescription)

        val pos = arguments?.getInt("POS", 0)

        when(pos){
            0 -> {
                imageView.setImageResource(R.drawable.vp0)
                title.text = "100% reliable dictionary"
                description.text = "More than 14,000 English dictionary books in your pocket"
            }
            1 -> {
                imageView.setImageResource(R.drawable.vp1)
                title.text = "Ability to save your favorite words"
                description.text = "Save the words you need because you will need them in the future"
            }
            2 -> {
                imageView.setImageResource(R.drawable.vp2)
                title.text = "Easily find a needle in a haystack"
                description.text = "You can easily find the word in the search section"
            }
            3 -> {
                imageView.setImageResource(R.drawable.vp3)
                title.text = "Help your friends come to America too"
                description.text = "Share the unique word through social networks"
            }
            4 -> {
                imageView.setImageResource(R.drawable.vp4)
                title.text = "Learn to pronounce the word"
                description.text = "Practice pronouncing the words..."
            }
            else ->{
                letsListener.invoke()
                imageView.setImageResource(R.drawable.vp5)
                title.text = "Hurry, America is waiting for you"
                description.text = "KHAN347 team will never leave you..."
            }

        }
    }
}