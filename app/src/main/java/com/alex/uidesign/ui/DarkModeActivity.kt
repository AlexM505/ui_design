package com.alex.uidesign.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alex.uidesign.R
import com.alex.uidesign.utils.SaveData
import kotlinx.android.synthetic.main.activity_dark_mode.*

class DarkModeActivity : AppCompatActivity() {

    private lateinit var saveData: SaveData

    override fun onCreate(savedInstanceState: Bundle?) {

        saveData = SaveData(this)
        if (saveData.loadDarkModeState() == true)
            setTheme(R.style.darkTheme)
        else
            setTheme(R.style.AppTheme)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dark_mode)


        if(saveData.loadDarkModeState() == true)
            sOnOffDark.isChecked = true

        sOnOffDark!!.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked){
                saveData.setDarkModeState(true)
                restartApplication()
            }else{
                saveData.setDarkModeState(false)
                restartApplication()
            }
        }

        btnBack.setOnClickListener {
            finish()
        }
    }

    private fun restartApplication() {
        val i = Intent(applicationContext,DarkModeActivity::class.java)
        startActivity(i)
        finish()
    }
}
