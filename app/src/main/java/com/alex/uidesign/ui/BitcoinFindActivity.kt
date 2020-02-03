package com.alex.uidesign.ui

import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.alex.uidesign.R
import kotlinx.android.synthetic.main.activity_bitcoin_find.*


class BitcoinFindActivity : AppCompatActivity() {

    var spotconim: Animation? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bitcoin_find)

        findcoins.startRippleAnimation()

        spotconim = AnimationUtils.loadAnimation(this, R.anim.spotconim)
        spotcon.startAnimation(spotconim)
        resultcon.startAnimation(spotconim)
    }
}
