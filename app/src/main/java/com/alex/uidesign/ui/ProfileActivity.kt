package com.alex.uidesign.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.alex.uidesign.R
import com.alex.uidesign.utils.SaveData
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity() {

    private var mMaxScrollSize:Int = 0
    private val PERCENTAGE_TO_ANIMATE_AVATAR = 50
    private var mIsAvatarShown = true

    private var mProfileImage: ImageView? = null
    private lateinit var saveData: SaveData

    override fun onCreate(savedInstanceState: Bundle?) {

        saveData = SaveData(this)
        if (saveData.loadDarkModeState() == true)
            setTheme(R.style.darkTheme)
        else
            setTheme(R.style.AppTheme)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val appbarLayout = findViewById<AppBarLayout>(R.id.appbar)
        mMaxScrollSize = appbarLayout.totalScrollRange

        mProfileImage = findViewById(R.id.profile_image)

        appbarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->

            if (mMaxScrollSize == 0) mMaxScrollSize = appBarLayout.totalScrollRange
            val percentage = Math.abs(verticalOffset) * 100 / mMaxScrollSize
            if (percentage >= PERCENTAGE_TO_ANIMATE_AVATAR && mIsAvatarShown) {
                mIsAvatarShown = false
                mProfileImage!!.animate()
                    .scaleY(0.6F).scaleX(0.6F)
                    .setDuration(300)
                    .start()
            }
            if (percentage <= PERCENTAGE_TO_ANIMATE_AVATAR && !mIsAvatarShown) {
                mIsAvatarShown = true
                mProfileImage!!.animate()
                    .scaleY(1F).scaleX(1F)
                    .start()
            }
        })


        btnBack.setOnClickListener {
            finish()
        }
    }
}
