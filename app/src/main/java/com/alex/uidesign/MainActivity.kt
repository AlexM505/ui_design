package com.alex.uidesign

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alex.uidesign.ui.BitcoinFindActivity
import com.alex.uidesign.ui.FoodMenuActivity
import com.alex.uidesign.ui.LottiesAnimationsActivity
import com.alex.uidesign.ui.ReaderQrActivity
import kotlinx.android.synthetic.main.activity_main.*
import nl.dionsegijn.konfetti.models.Shape
import nl.dionsegijn.konfetti.models.Size


class MainActivity : AppCompatActivity() {

    private val url = "https://github.com/AlexM505"

    override fun onCreate(savedInstanceState: Bundle?) {
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
}
