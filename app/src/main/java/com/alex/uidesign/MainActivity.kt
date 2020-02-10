package com.alex.uidesign

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.alex.uidesign.ui.*
import com.alex.uidesign.utils.SaveData
import kotlinx.android.synthetic.main.activity_main.*
import nl.dionsegijn.konfetti.models.Shape
import nl.dionsegijn.konfetti.models.Size


class MainActivity : AppCompatActivity() {

    private val url = "https://github.com/AlexM505"
    private lateinit var saveData: SaveData

    override fun onCreate(savedInstanceState: Bundle?) {

        saveData = SaveData(this)
        if (saveData.loadDarkModeState() == true)
            setTheme(R.style.darkTheme)
        else
            setTheme(R.style.AppTheme)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cvPresentation.setOnClickListener {
            konfetti()
        }

        github.setOnClickListener {
            val uri: Uri = Uri.parse(url)
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }

        cvFoodMenu.setOnClickListener {
            startActivity(Intent(this,FoodMenuActivity::class.java))
        }

        cvBitcoinFind.setOnClickListener {
            startActivity(Intent(this,BitcoinFindActivity::class.java))
        }

        cvLottieAnimations.setOnClickListener {
            startActivity(Intent(this,LottiesAnimationsActivity::class.java))
        }

        cvReaderQr.setOnClickListener {
            startActivity(Intent(this,ReaderQrActivity::class.java))
        }

        cvMapBox.setOnClickListener {
            startActivity(Intent(this,MapBoxActivity::class.java))
        }

        cvNightMode.setOnClickListener{
            startActivity(Intent(this,DarkModeActivity::class.java))
        }
    }

    private fun konfetti() {
        viewKonfetti.build()
            .addColors(Color.CYAN, Color.YELLOW, Color.GREEN, Color.BLUE,Color.WHITE,Color.RED)
            .setDirection(0.0, 359.0)
            .setSpeed(1f, 5f)
            .setFadeOutEnabled(true)
            .setTimeToLive(2000L)
            .addShapes(Shape.RECT, Shape.CIRCLE)
            .addSizes(Size(12))
            .setPosition(viewKonfetti.x + viewKonfetti.width / 2, viewKonfetti.y + viewKonfetti.height / 3)
            .burst(100)

//            .streamFor(300, 5000L)
    }

    override fun onRestart() {
        super.onRestart()

        restartApplication()
    }

    private fun restartApplication() {
        val i = Intent(applicationContext,MainActivity::class.java)
        startActivity(i)
        finish()
    }
}
