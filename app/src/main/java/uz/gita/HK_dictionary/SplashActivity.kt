package uz.gita.dictionarynewhk

import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.DecelerateInterpolator
import android.widget.ProgressBar
import uz.gita.HK_dictionary.R
import uz.gita.HK_dictionary.presenter.screen.activity.MainActivity
import uz.gita.dictionarynewhk.data.local.pref.MySharedPref
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class SplashActivity : AppCompatActivity() {
    private lateinit var procces: ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        procces = findViewById(R.id.progressBar)

        val animation = ObjectAnimator.ofInt(procces, "progress", 0, 100)
        animation.duration = 3000
        animation.interpolator = DecelerateInterpolator()
        animation.start()
        val thread = Executors.newSingleThreadExecutor()
        thread.execute {
            thread.awaitTermination(2500, TimeUnit.MILLISECONDS)
            if (MySharedPref.getInstance().first){
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            }else{
                startActivity(Intent(this@SplashActivity, VpActivity::class.java))
            }
            thread.shutdown()
        }
    }

    override fun onRestart() {
        super.onRestart()
        finish()
    }
}