package com.alex.uidesign.ui

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.SparseArray
import android.view.SurfaceHolder
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.util.isNotEmpty
import com.alex.uidesign.R
import com.google.android.gms.vision.CameraSource
import com.google.android.gms.vision.Detector
import com.google.android.gms.vision.barcode.Barcode
import com.google.android.gms.vision.barcode.BarcodeDetector
import kotlinx.android.synthetic.main.activity_reader_qr.*

class ReaderQrActivity : AppCompatActivity() {

    private val requestCodeCameraPermission = 1001
    private lateinit var cameraSource: CameraSource
    private lateinit var detector: BarcodeDetector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reader_qr)

        if(ContextCompat.checkSelfPermission(this,Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            askForCameraPermission()
        }else{
            setupControls()
        }

        ivBack.setOnClickListener {
            finish()
        }
    }

    private fun setupControls(){
        detector= BarcodeDetector.Builder(this).build()
        cameraSource= CameraSource.Builder(this,detector)
            .setAutoFocusEnabled(true)
            .build()

        surfaceViewCamara.holder.addCallback(surfaceCallBack)
        detector.setProcessor(processor)
    }

    private fun askForCameraPermission(){
        ActivityCompat.requestPermissions(this,
            arrayOf(Manifest.permission.CAMERA),
            requestCodeCameraPermission
        )
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if(requestCode == requestCodeCameraPermission && grantResults.isNotEmpty()){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                setupControls()
            }else{
                Toast.makeText(applicationContext,"Permisos denegados",Toast.LENGTH_SHORT).show()
            }
        }
    }

    private val surfaceCallBack = object : SurfaceHolder.Callback{
        override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {

        }

        override fun surfaceDestroyed(holder: SurfaceHolder?) {
            cameraSource.stop()
        }

        override fun surfaceCreated(holder: SurfaceHolder?) {
            try {
                cameraSource.start(holder)
            }catch (exception: Exception){
                Toast.makeText(applicationContext,"Esta ocurriendo un error con la camara!",Toast.LENGTH_SHORT).show()
                System.out.println(">>Error en surfaceCreated: " + exception.message)
            }
        }

    }

    private val processor = object : Detector.Processor<Barcode>{
        override fun release() {

        }

        override fun receiveDetections(detections: Detector.Detections<Barcode>?) {

            if(detections != null && detections.detectedItems.isNotEmpty()){
                val qrCodes: SparseArray<Barcode> = detections.detectedItems
                val code = qrCodes.valueAt(0)
                tvScanQrResult.text = code.displayValue
            }else{
                tvScanQrResult.text = "Apunta a un codigo QR o de barra "
            }
        }

    }
}
