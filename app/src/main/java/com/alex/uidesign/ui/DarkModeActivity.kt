package com.alex.uidesign.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.alex.uidesign.R
import com.alex.uidesign.utils.ConnectivityLiveData
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

        if(saveData.loadDarkModeState() == true) {
            sOnOffDark.isChecked = true
            ivWifi.setImageResource(R.drawable.ic_wifi_white_24dp)
        }else{
            ivWifi.setImageResource(R.drawable.ic_wifi_black_24dp)
        }


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

        checkConnectivity()

    }

    private fun restartApplication() {
        val i = Intent(applicationContext,DarkModeActivity::class.java)
        startActivity(i)
        finish()
    }

    private fun checkConnectivity(){
        val connectionLiveData = ConnectivityLiveData(applicationContext)
        connectionLiveData.observe(this,
            Observer { connection ->
                if (connection!!.isConnected)
                    tvConectivity.text = connection.type
                else
                    tvConectivity.text = connection.type

            })
    }
}
