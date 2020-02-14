package com.alex.uidesign.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.alex.uidesign.R
import com.alex.uidesign.ui.adapters.DotIndicatorPager2Adapter
import com.alex.uidesign.utils.SaveData
import com.alex.uidesign.utils.ZoomOutPageTransformer
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator
import com.tbuonomo.viewpagerdotsindicator.SpringDotsIndicator
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator
import kotlinx.android.synthetic.main.activity_view_pager2.*

class ViewPager2Activity : AppCompatActivity() {

    private lateinit var saveData: SaveData

    override fun onCreate(savedInstanceState: Bundle?) {

        saveData = SaveData(this)
        if (saveData.loadDarkModeState() == true)
            setTheme(R.style.darkTheme)
        else
            setTheme(R.style.AppTheme)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager2)

        val dotsIndicator = findViewById<DotsIndicator>(R.id.dots_indicator)
        val springDotsIndicator = findViewById<SpringDotsIndicator>(R.id.spring_dots_indicator)
        val wormDotsIndicator = findViewById<WormDotsIndicator>(R.id.worm_dots_indicator)

        val viewPager2 = findViewById<ViewPager2>(R.id.view_pager2)
        val adapter = DotIndicatorPager2Adapter()
        viewPager2.adapter = adapter

        //Efecto de deslizamiento
//        val zoomOutPageTransformer = ZoomOutPageTransformer()
//        viewPager2.setPageTransformer { page, position ->
//            zoomOutPageTransformer.transformPage(page, position)
//        }

        dotsIndicator.setViewPager2(viewPager2)
        springDotsIndicator.setViewPager2(viewPager2)
        wormDotsIndicator.setViewPager2(viewPager2)

        btnBack.setOnClickListener { finish() }

    }
}
