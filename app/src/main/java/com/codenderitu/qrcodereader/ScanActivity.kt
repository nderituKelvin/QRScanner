package com.codenderitu.qrcodereader

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import com.google.zxing.Result
import me.dm7.barcodescanner.zxing.ZXingScannerView

class ScanActivity : AppCompatActivity(), ZXingScannerView.ResultHandler {
    private lateinit var scannerView: ZXingScannerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        scannerView = ZXingScannerView(applicationContext)
        setContentView(scannerView)
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), 1)
        }
    }

    override fun onResume() {
        super.onResume()
        scannerView.setResultHandler(this)
        scannerView.startCamera()
    }

    override fun onPause() {
        super.onPause()
        scannerView.stopCamera()

    }

    override fun handleResult(p0: Result?) {
        val returnIntent = Intent()
        returnIntent.putExtra("result", p0?.text)
        setResult(Activity.RESULT_OK, returnIntent)
        finish()
    }

}
