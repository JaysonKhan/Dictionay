package uz.gita.dictionarynewhk

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatButton
import androidx.viewpager2.widget.ViewPager2
import com.tbuonomo.viewpagerdotsindicator.SpringDotsIndicator
import uz.gita.HK_dictionary.R
import uz.gita.HK_dictionary.presenter.adapter.ViewPagerAdapter
import uz.gita.dictionarynewhk.data.local.pref.MySharedPref

class VpActivity : AppCompatActivity() {
    private lateinit var viewPager : ViewPager2
    private lateinit var dots: SpringDotsIndicator
    private lateinit var letsGo: AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vp)

        viewPager = findViewById(R.id.myViewPager)
        dots = findViewById(R.id.spring_dots_indicator)
        letsGo = findViewById(R.id.btn_letsgo)
        val adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        adapter.setListener {
            letsGo.visibility = View.VISIBLE
        }

        letsGo.setOnClickListener {
            startActivity(Intent(this@VpActivity, SplashActivity::class.java))
            MySharedPref.getInstance().first = true
        }
        viewPager.adapter = adapter

        dots.attachTo(viewPager)
    }

    override fun onRestart() {
        super.onRestart()
        finish()
    }
}